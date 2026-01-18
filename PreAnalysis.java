package algorithm;

/**
 * PreAnalysis interface for students to implement their algorithm selection logic
 * 
 * Students should analyze the characteristics of the text and pattern to determine
 * which algorithm would be most efficient for the given input.
 * 
 * The system will automatically use this analysis if the chooseAlgorithm method
 * returns a non-null value.
 */
public abstract class PreAnalysis {
    
    /**
     * Analyze the text and pattern to choose the best algorithm
     * 
     * @param text The text to search in
     * @param pattern The pattern to search for
     * @return The name of the algorithm to use (e.g., "Naive", "KMP", "RabinKarp", "BoyerMoore", "GoCrazy")
     *         Return null if you want to skip pre-analysis and run all algorithms
     * 
     * Tips for students:
     * - Consider the length of the text and pattern
     * - Consider the characteristics of the pattern (repeating characters, etc.)
     * - Consider the alphabet size
     * - Think about which algorithm performs best in different scenarios
     */
    public abstract String chooseAlgorithm(String text, String pattern);
    
    /**
     * Get a description of your analysis strategy
     * This will be displayed in the output
     */
    public abstract String getStrategyDescription();
}


/**
 * Default implementation that students should modify
 * This is where students write their pre-analysis logic
 */
class StudentPreAnalysis extends PreAnalysis {
    
    @Override
    public String chooseAlgorithm(String text, String pattern) {
        // TODO: Students should implement their analysis logic here
        // 
        // Example considerations:
        // - If pattern is very short, Naive might be fastest
        // - If pattern has repeating prefixes, KMP is good
        // - If pattern is long and text is very long, RabinKarp might be good
        // - If alphabet is small, Boyer-Moore can be very efficient
        //
        // For now, this returns null which means "run all algorithms"
        // Students should replace this with their logic
        
        
    int n = text.length();
    int m = pattern.length();

    
    if (m <= 3 || m == 0 || n == 0 || m > n) {
        return "Naive";
    }

    if(m>=4 && m <= 50 && !(isSameCharPattern(pattern) || isGoodForBM(pattern))){
        return "GoCrazy";
    }
    
    if (isSameCharPattern(pattern)) {
        return "KMP";
    }

    if (hasRepeatingPrefix(pattern)) {
        return "KMP";
    }

    

    
    if (isGoodForBM(pattern)) {
        return "BoyerMoore";
    }

    if (n > 2000 && m > 5) {
        return "BoyerMoore";
    }

   
    if (m > 10 && n > 1000) {
        return "RabinKarp";
    }

   
    return "BoyerMoore";
        
        //return null; // Return null to run all algorithms, or return algorithm name to use pre-analysis
    }
    
    @Override
    public String getStrategyDescription() {
        return "Default strategy - no pre-analysis implemented yet (students should implement this)";
    }

   private boolean isSameCharPattern(String pattern) {
    for (int i = 1; i < pattern.length(); i++) {
        if (pattern.charAt(i) != pattern.charAt(0))
            return false;
    }
    return true;
}

private boolean hasRepeatingPrefix(String pattern) {
    int m = pattern.length();
    for (int i = 1; i <= m / 2; i++) {
        if (pattern.startsWith(pattern.substring(0, i), i))
            return true;
    }
    return false;
}



private boolean isGoodForBM(String pattern) {
    
    char last = pattern.charAt(pattern.length() - 1);
    int freq = 0;

    for (int i = 0; i < pattern.length(); i++) {
        if (pattern.charAt(i) == last) freq++;
    }
    return freq < pattern.length() / 3; 
}


}


/**
 * Example implementation showing how pre-analysis could work
 * This is for demonstration purposes
 */
class ExamplePreAnalysis extends PreAnalysis {

    @Override
    public String chooseAlgorithm(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();

        // Simple heuristic example
        if (patternLen <= 3) {
            return "Naive"; // For very short patterns, naive is often fastest
        } else if (hasRepeatingPrefix(pattern)) {
            return "KMP"; // KMP is good for patterns with repeating prefixes
        } else if (patternLen > 10 && textLen > 1000) {
            return "RabinKarp"; // RabinKarp can be good for long patterns in long texts
        } else {
            return "Naive"; // Default to naive for other cases
        }
    }

    private boolean hasRepeatingPrefix(String pattern) {
        if (pattern.length() < 2) return false;

        // Check if first character repeats
        char first = pattern.charAt(0);
        int count = 0;
        for (int i = 0; i < Math.min(pattern.length(), 5); i++) {
            if (pattern.charAt(i) == first) count++;
        }
        return count >= 3;
    }

    @Override
    public String getStrategyDescription() {
        return "Example strategy: Choose based on pattern length and characteristics";
    }
}


/**
 * Instructor's pre-analysis implementation (for testing purposes only)
 * Students should NOT modify this class
 */
class InstructorPreAnalysis extends PreAnalysis {

    @Override
    public String chooseAlgorithm(String text, String pattern) {
        // This is a placeholder for instructor testing
        // Students should focus on implementing StudentPreAnalysis
        return null;
    }

    @Override
    public String getStrategyDescription() {
        return "Instructor's testing implementation";
    }
}
