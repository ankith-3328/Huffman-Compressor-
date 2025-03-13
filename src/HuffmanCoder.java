import java.util.*;

class HuffmanCoder {
    private final HashMap<Character, String> encoder;
    private final HashMap<String, Character> decoder;

    private static class Node implements Comparable<Node> {
        Character character;
        int frequency;
        Node left, right;

        public Node(Character character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node other) {
            return this.frequency - other.frequency;
        }
    }

    public HuffmanCoder(String feeder) {
        // Frequency map
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : feeder.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Priority Queue to build Huffman Tree
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            minHeap.offer(new Node(entry.getKey(), entry.getValue()));
        }

        // Build Huffman Tree
        while (minHeap.size() > 1) {
            Node first = minHeap.poll();
            Node second = minHeap.poll();
            Node merged = new Node(null, first.frequency + second.frequency);
            merged.left = first;
            merged.right = second;
            minHeap.offer(merged);
        }

        Node root = minHeap.poll();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();

        // Initialize encoder and decoder maps
        initEncoderDecoder(root, "");
    }

    private void initEncoderDecoder(Node node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            encoder.put(node.character, code);
            decoder.put(code, node.character);
            return;
        }
        initEncoderDecoder(node.left, code + "0");
        initEncoderDecoder(node.right, code + "1");
    }

    public String encode(String source) {
        StringBuilder ans = new StringBuilder();
        for (char c : source.toCharArray()) {
            ans.append(encoder.get(c));
        }
        return ans.toString();
    }

    public String decode(String codedString) {
        StringBuilder ans = new StringBuilder();
        String key = "";
        for (char c : codedString.toCharArray()) {
            key += c;
            if (decoder.containsKey(key)) {
                ans.append(decoder.get(key));
                key = "";
            }
        }
        return ans.toString();
    }
}
