package dna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DNACodon {
    private Map<String, List<String>> aminoAcids;
    private Map<String, String> codons;

    /**
     * Sets up the DNA Codon values
     * Note: There is a constraint on the codon to amino acid relation.
     * Sometimes two triples of letters will translate into the same protein,
     * but no triple will translate into more than one protein.
     */
    public DNACodon() {
        // initialize the maps
        aminoAcids = new HashMap<>();
        codons = new HashMap<>();

        // Codons from wikipedia: https://en.wikipedia.org/wiki/DNA_codon_table

        aminoAcids.put("ala", Arrays.asList("GCT", "GCC", "GCA", "GCG"));
        aminoAcids.put("arg", Arrays.asList("CGT", "CGC", "CGA", "CGG", "AGA", "AGG"));
        aminoAcids.put("asn", Arrays.asList("AAT", "AAC"));
        aminoAcids.put("asp", Arrays.asList("GAT", "GAC"));
        // there are some codons that can translate to both asp or asn, but due
        // to the constraint we will not include them

        aminoAcids.put("cys", Arrays.asList("TGT", "TGC"));
        aminoAcids.put("gln", Arrays.asList("CAA", "CAG"));
        aminoAcids.put("glu", Arrays.asList("GAA", "GAG"));
        aminoAcids.put("gly", Arrays.asList("GGT", "GGC", "GGA", "GGG"));

        aminoAcids.put("his", Arrays.asList("CAT", "CAC"));

        // aminoAcids.put("start", List.of("ATG"));
        // we don't include start as ATG already trancribes to start

        aminoAcids.put("lle", Arrays.asList("ATT", "ATC", "ATA"));
        aminoAcids.put("leu", Arrays.asList("CTT", "CTC", "CTA", "CTG", "TTA", "TTG"));
        aminoAcids.put("lys", Arrays.asList("AAA", "AAG"));

        aminoAcids.put("met", Arrays.asList("ATG"));

        aminoAcids.put("phe", Arrays.asList("TTT", "TTC"));
        aminoAcids.put("pro", Arrays.asList("CCT", "CCC", "CCA", "CCG"));

        aminoAcids.put("ser", Arrays.asList("TCT", "TCC", "TCA", "TCG", "AGT", "AGC"));

        aminoAcids.put("thr", Arrays.asList("ACT", "ACC", "ACA", "ACG"));
        aminoAcids.put("trp", Arrays.asList("TGG"));
        aminoAcids.put("tyr", Arrays.asList("TAT", "TAC"));

        aminoAcids.put("val", Arrays.asList("GTT", "GTC", "GTA", "GTG"));

        aminoAcids.put("stop", Arrays.asList("TAA", "TGA", "TAG"));

        // We will also have a inverse map for quick amino acid lookup
        for (Map.Entry<String, List<String>> entry : aminoAcids.entrySet()) {
            String acid = entry.getKey();
            List<String> codonList = entry.getValue();

            // we will iterate over all the codons and build the map from codon -> acid
            for (String codon : codonList) {
                this.codons.put(codon, acid);
            }
        }
    }

    /**
     * Returns a list of codons for a given amino acid.
     *
     * @param aminoacid the three letter amino acid
     * @return the list of codons
     */
    public List<String> codonsFor(String aminoacid) throws Exception {
        if (aminoacid.length() != 3 && !aminoacid.equals("stop")) {
            throw new Exception("incorrect amino acid");
        }
        aminoacid = aminoacid.toLowerCase();
        if (this.aminoAcids.containsKey(aminoacid)) {
            return this.aminoAcids.get(aminoacid);
        }
        throw new Exception("this amino acid has no codons:" + aminoacid);
    }

    /**
     * Returns the amino acid for a codon.
     *
     * @param codon the three letter codon
     * @return the amino acid transcribed by the codon
     */
    public String acidFor(String codon) throws Exception {
        if (codon.length() != 3) {
            throw new Exception("incorrect codon. must be three letters");
        }
        codon = codon.toUpperCase();
        if (this.codons.containsKey(codon)) {
            return this.codons.get(codon);
        }
        throw new Exception("this codon does not transcribe a amino acid: " + codon);
    }
}
