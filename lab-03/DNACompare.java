import java.util.ArrayList;
import java.util.HashMap;

public class DNACompare {

    // 1. Convert DNA string to codons (groups of 3)
    public static ArrayList<String> DNAToCodons(String dna) {
        ArrayList<String> codons = new ArrayList<>();
        for (int i = 0; i + 2 < dna.length(); i += 3) {
            codons.add(dna.substring(i, i + 3));
        }
        return codons;
    }

    // 2. Convert codon to amino acid (1-letter code)
    public static String CodonToAminoAcid(String codon) {
        codon = codon.toUpperCase();
        HashMap<String, String> codonTable = new HashMap<>();

        // Populate codon table (standard genetic code, DNA version: T instead of U)
        codonTable.put("TTT", "F"); codonTable.put("TTC", "F");
        codonTable.put("TTA", "L"); codonTable.put("TTG", "L");
        codonTable.put("CTT", "L"); codonTable.put("CTC", "L");
        codonTable.put("CTA", "L"); codonTable.put("CTG", "L");
        codonTable.put("ATT", "I"); codonTable.put("ATC", "I");
        codonTable.put("ATA", "I"); codonTable.put("ATG", "M");
        codonTable.put("GTT", "V"); codonTable.put("GTC", "V");
        codonTable.put("GTA", "V"); codonTable.put("GTG", "V");
        codonTable.put("TCT", "S"); codonTable.put("TCC", "S");
        codonTable.put("TCA", "S"); codonTable.put("TCG", "S");
        codonTable.put("CCT", "P"); codonTable.put("CCC", "P");
        codonTable.put("CCA", "P"); codonTable.put("CCG", "P");
        codonTable.put("ACT", "T"); codonTable.put("ACC", "T");
        codonTable.put("ACA", "T"); codonTable.put("ACG", "T");
        codonTable.put("GCT", "A"); codonTable.put("GCC", "A");
        codonTable.put("GCA", "A"); codonTable.put("GCG", "A");
        codonTable.put("TAT", "Y"); codonTable.put("TAC", "Y");
        codonTable.put("TAA", "*"); codonTable.put("TAG", "*"); // stop codons
        codonTable.put("CAT", "H"); codonTable.put("CAC", "H");
        codonTable.put("CAA", "Q"); codonTable.put("CAG", "Q");
        codonTable.put("AAT", "N"); codonTable.put("AAC", "N");
        codonTable.put("AAA", "K"); codonTable.put("AAG", "K");
        codonTable.put("GAT", "D"); codonTable.put("GAC", "D");
        codonTable.put("GAA", "E"); codonTable.put("GAG", "E");
        codonTable.put("TGT", "C"); codonTable.put("TGC", "C");
        codonTable.put("TGA", "*"); codonTable.put("TGG", "W");
        codonTable.put("CGT", "R"); codonTable.put("CGC", "R");
        codonTable.put("CGA", "R"); codonTable.put("CGG", "R");
        codonTable.put("AGT", "S"); codonTable.put("AGC", "S");
        codonTable.put("AGA", "R"); codonTable.put("AGG", "R");
        codonTable.put("GGT", "G"); codonTable.put("GGC", "G");
        codonTable.put("GGA", "G"); codonTable.put("GGG", "G");

        return codonTable.getOrDefault(codon, "?"); // ? for invalid codons
    }

    // 3. Convert DNA sequence to amino acid sequence
    public static ArrayList<String> dna_to_amino_acid(String dna) {
        ArrayList<String> codons = DNAToCodons(dna);
        ArrayList<String> aminoAcids = new ArrayList<>();
        for (String codon : codons) {
            aminoAcids.add(CodonToAminoAcid(codon));
        }
        return aminoAcids;
    }

    // 4. Compare two amino acid sequences
    public static boolean is_match(ArrayList<String> amino_seq1, ArrayList<String> amino_seq2) {
        if (amino_seq1.size() != amino_seq2.size()) return false;
        for (int i = 0; i < amino_seq1.size(); i++) {
            if (!amino_seq1.get(i).equals(amino_seq2.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Main method
    public static void main(String[] args) {
        String DNA1 = "CTGATATTGTATCCGGCCGAA";
        String DNA2 = "CTAGCCGGTGGTTATTAATAGTAAACTATTCCA";
        String DNA3 = "TTAATCCTCTACCCCGCAGAG";

        ArrayList<String> aa1 = dna_to_amino_acid(DNA1);
        ArrayList<String> aa2 = dna_to_amino_acid(DNA2);
        ArrayList<String> aa3 = dna_to_amino_acid(DNA3);

        System.out.println("DNA1 -> " + aa1);
        System.out.println("DNA2 -> " + aa2);
        System.out.println("DNA3 -> " + aa3);

        System.out.println("\nComparisons:");
        System.out.println("DNA1 vs DNA2: " + is_match(aa1, aa2));
        System.out.println("DNA1 vs DNA3: " + is_match(aa1, aa3));
        System.out.println("DNA2 vs DNA3: " + is_match(aa2, aa3));
    }
}
