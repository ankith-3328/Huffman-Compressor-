class Main{
    public static void main(String[] args) {
        String s = "aabcaabdebaacd";
        HuffmanCoder hf = new HuffmanCoder(s);

        String encoded = hf.encode(s);
        System.out.println(encoded);

        String decoded = hf.decode(encoded);
        System.out.println(decoded);
    }
}