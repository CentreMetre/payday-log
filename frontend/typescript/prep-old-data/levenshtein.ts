export function levenshtein(a: string, b: string): number {
    const m = a.length;
    const n = b.length;
    const dp: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));

    for (let i = 0; i <= m; i++) dp[i]![0] = i;
    for (let j = 0; j <= n; j++) dp[0]![j] = j;

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            const cost = a[i - 1]! === b[j - 1]! ? 0 : 1;
            dp[i]![j] = Math.min(
                dp[i - 1]![j]! + 1,
                dp[i]![j - 1]! + 1,
                dp[i - 1]![j - 1]! + cost
            );
        }
    }

    return dp[m]![n]!;
}

export function levenshteinClosest(a: string, stringArray: string[]): Map<string, number> {
    let lowestScore = Infinity;

    const lowestScoringValues: Map<string, number> = new Map();

    for (const string of stringArray)
    {
        const score = levenshtein(a, string)
        if (score < lowestScore) {
            lowestScore = score;
            lowestScoringValues.clear();
            lowestScoringValues.set(string, score);
        }
        if (score === lowestScore) {
            lowestScoringValues.set(string, score);
        }
    }

    return lowestScoringValues
}