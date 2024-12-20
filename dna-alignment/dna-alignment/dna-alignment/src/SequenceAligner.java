import java.util.Random;

/**
 * SequenceAligner class performs DNA sequence alignment using dynamic programming.
 * It aligns two DNA strands by minimizing gaps and mismatches, providing the optimal alignment score.
 */
public class SequenceAligner {
    private static Random gen = new Random();

    private String x, y; // Input DNA strands
    private int n, m; // Lengths of x and y
    private String alignedX, alignedY; // Aligned DNA strands
    private Result[][] cache; // Cache table for dynamic programming
    private Judge judge; // Scoring judge for alignment

    /**
     * Generates a pair of random DNA strands, aligns them using the default judge.
     */
    public SequenceAligner(int n) {
        this(randomDNA(n), randomDNA(n - gen.nextInt(n / 2) * (gen.nextInt(2) * 2 - 1)));
    }

    /**
     * Aligns the given strands using the default judge.
     */
    public SequenceAligner(String x, String y) {
        this(x, y, new Judge());
    }

    /**
     * Aligns the given strands using the specified judge.
     */
    public SequenceAligner(String x, String y, Judge judge) {
        this.x = x.toUpperCase();
        this.y = y.toUpperCase();
        this.judge = judge;
        n = x.length();
        m = y.length();
        cache = new Result[n + 1][m + 1];
        fillCache();
        traceback();
    }

    // Accessor methods

    public String getX() { return x; }
    public String getY() { return y; }
    public Judge getJudge() { return judge; }
    public String getAlignedX() { return alignedX; }
    public String getAlignedY() { return alignedY; }

    /**
     * Fills the dynamic programming cache table to compute alignment scores.
     * Each cell in the table represents the optimal alignment score up to that point.
     */
    private void fillCache() {
        cache[0][0] = new Result(0); // Base case: aligning empty substrings

        // Fill first column (gaps in y)
        for (int i = 1; i <= n; i++) {
            cache[i][0] = new Result(cache[i - 1][0].getScore() + judge.getGapCost(), Direction.UP);
        }

        // Fill first row (gaps in x)
        for (int j = 1; j <= m; j++) {
            cache[0][j] = new Result(cache[0][j - 1].getScore() + judge.getGapCost(), Direction.LEFT);
        }

        // Fill the rest of the table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int diag = cache[i - 1][j - 1].getScore() + judge.score(x.charAt(i - 1), y.charAt(j - 1));
                int left = cache[i][j - 1].getScore() + judge.getGapCost();
                int up = cache[i - 1][j].getScore() + judge.getGapCost();

                // Determine the optimal direction based on maximum score
                Direction dir;
                int maxScore;

                if (diag >= left && diag >= up) {
                    maxScore = diag;
                    dir = Direction.DIAGONAL;
                } else if (left >= up) {
                    maxScore = left;
                    dir = Direction.LEFT;
                } else {
                    maxScore = up;
                    dir = Direction.UP;
                }

                cache[i][j] = new Result(maxScore, dir);
            }
        }
    }

    /**
     * Returns the result of solving the alignment problem for the first i characters of x and j characters of y.
     */
    public Result getResult(int i, int j) {
        if (i < 0 || i > n || j < 0 || j > m) {
            throw new IllegalArgumentException("Indices are out of bounds.");
        }
        return cache[i][j];
    }

    /**
     * Traces back through the cache table to construct the aligned strands.
     * Marks the optimal alignment path in the cache.
     */
    private void traceback() {
        int i = n, j = m;
        StringBuilder alignedXBuilder = new StringBuilder();
        StringBuilder alignedYBuilder = new StringBuilder();

        while (i > 0 || j > 0) {
            Result current = cache[i][j];
            current.markPath();

            if (current.getParent() == Direction.DIAGONAL) {
                alignedXBuilder.insert(0, x.charAt(i - 1));
                alignedYBuilder.insert(0, y.charAt(j - 1));
                i--;
                j--;
            } else if (current.getParent() == Direction.LEFT) {
                alignedXBuilder.insert(0, Constants.GAP_CHAR);
                alignedYBuilder.insert(0, y.charAt(j - 1));
                j--;
            } else {
                alignedXBuilder.insert(0, x.charAt(i - 1));
                alignedYBuilder.insert(0, Constants.GAP_CHAR);
                i--;
            }
        }

        // Handle remaining gaps
        while (i > 0) {
            alignedXBuilder.insert(0, x.charAt(i - 1));
            alignedYBuilder.insert(0, Constants.GAP_CHAR);
            i--;
        }
        while (j > 0) {
            alignedXBuilder.insert(0, Constants.GAP_CHAR);
            alignedYBuilder.insert(0, y.charAt(j - 1));
            j--;
        }

        alignedX = alignedXBuilder.toString();
        alignedY = alignedYBuilder.toString();
    }

    /**
     * Verifies if the strands are aligned properly.
     */
    public boolean isAligned() {
        return alignedX != null && alignedY != null && alignedX.length() == alignedY.length();
    }

    /**
     * Computes the alignment score using the judge.
     */
    public int getScore() {
        return isAligned() ? judge.score(alignedX, alignedY) : 0;
    }

    /**
     * Generates a textual representation of the alignment, showing matches and mismatches.
     */
    public String toString() {
        if (!isAligned()) return "[X=" + x + ",Y=" + y + "]";

        final char GAP_SYM = '.', MATCH_SYM = '|', MISMATCH_SYM = ':';
        StringBuilder representation = new StringBuilder();
        representation.append(alignedX).append('\n');

        for (int i = 0; i < alignedX.length(); i++) {
            if (alignedX.charAt(i) == Constants.GAP_CHAR || alignedY.charAt(i) == Constants.GAP_CHAR)
                representation.append(GAP_SYM);
            else if (alignedX.charAt(i) == alignedY.charAt(i))
                representation.append(MATCH_SYM);
            else
                representation.append(MISMATCH_SYM);
        }

        representation.append('\n').append(alignedY).append('\n');
        representation.append("score = ").append(getScore());
        return representation.toString();
    }

    /**
     * Generates a random DNA strand of specified length.
     */
    private static String randomDNA(int n) {
        StringBuilder strand = new StringBuilder();
        for (int i = 0; i < n; i++) {
            strand.append("ACGT".charAt(gen.nextInt(4)));
        }
        return strand.toString();
    }
}
