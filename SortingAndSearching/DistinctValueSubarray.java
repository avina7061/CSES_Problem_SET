//package CSES_Problem_SET.SortingAndSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class DistinctValueSubarray {
    static final int MAXN = 300006;
    static ArrayList<Integer>[] divisors;

    public static void computeDivisors() {
        divisors = new ArrayList[MAXN + 1];
        for (int i = 1; i <= MAXN; i++) {
            divisors[i] = new ArrayList<>();
        }

        for (int i = 1; i <= MAXN; i++) {
            for (int j = i; j <= MAXN; j += i) {
                divisors[j].add(i);
            }
        }
    }


    /* ---------- SOLVE ---------- */
    public static int find(ArrayList<Integer> list, int ind) {
        int left = 0;
        int right = list.size() - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= ind) {
                ans = list.get(mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        long ans = 0;
        while (right < n) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            while (map.size() != (right - left + 1)) {
                map.put(arr[left], map.getOrDefault(arr[left], 0) - 1);
                if (map.get(arr[left]) == 0) map.remove(arr[left]);
                left++;
            }
            if (map.size() == (right - left + 1))
                ans += right - left + 1;
            ;
            right++;
        }
        System.out.println(ans);

    }


    public static long maximumSubarraySum(long arr[]) {
        long max = arr[0];
        long sum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sum = Math.max(arr[i], sum + arr[i]);
            max = Math.max(max, sum);
        }
        return max;
    }

    public static long minimumSubarraySum(long arr[]) {
        long min = arr[0];
        long sum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            if (sum > arr[i]) sum = arr[i];
            min = Math.min(min, sum);
        }
        return min;
    }

    /* ---------------- UTILITIES ---------------- */
    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long modPow(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    static List<Integer> toBits(long num) {
        List<Integer> bits = new ArrayList<>();
        if (num == 0) {
            bits.add(0);
            return bits;
        }
        while (num > 0) {
            bits.add((int) (num & 1));
            num >>= 1;
        }
        Collections.reverse(bits);
        return bits;
    }

    public static int countBits(long n) {
        return Long.bitCount(n);
    }

    static long[] prefixSum(long[] arr) {
        long[] pref = new long[arr.length + 1];
        for (int i = 0; i < arr.length; i++) pref[i + 1] = pref[i] + arr[i];
        return pref;
    }

    static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }

    static long abs(long x) {
        return x < 0 ? -x : x;
    }

    static long max(long a, long b) {
        return Math.max(a, b);
    }

    static long min(long a, long b) {
        return Math.min(a, b);
    }

    /* ---------------- SIEVE WITH SPF ---------------- */
    static int[] spf;
    static ArrayList<Integer> primes = new ArrayList<>();
    static HashSet<Integer> set = new HashSet<>();

    static void sieve(int n) {
        spf = new int[n + 1];

        for (int i = 1; i <= n; i++)
            spf[i] = i;

        for (int i = 2; i * i <= n; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= n; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (spf[i] == i) {
                primes.add(i);
                set.add(i);
            }
        }
    }

    static ArrayList<Integer> getPrimeFactors(int x) {
        ArrayList<Integer> factors = new ArrayList<>();

        while (x != 1) {
            factors.add(spf[x]);
            x /= spf[x];
        }

        return factors;
    }


    /* ---------------- FAST INPUT ---------------- */
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    /* ---------------- MAIN ---------------- */
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        // Call sieve for numbers up to 1,000,000 if you need
//        sieve(1000000);
        //        initFact(200000);
        //        computeDivisors();
        int t = 1;
//        t = sc.nextInt();


        while (t-- > 0) solve(sc);
        out.flush();
    }
}