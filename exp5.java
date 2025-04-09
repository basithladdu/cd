import java.util.*;
public class exp5 {
    static final char EPS = '9';
    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Non‑terminals: ");
        String nts = sc.nextLine().trim();
        System.out.print("Enter the Terminals: ");
        String ts = sc.nextLine().trim();
        Map<Character, List<String>> G = new LinkedHashMap<>();
        for (char A : nts.toCharArray()) {
            System.out.print("Enter the number of productions for " + A + ": ");
            int n = Integer.parseInt(sc.nextLine().trim());
            List<String> p = new ArrayList<>();
            System.out.println("Enter the productions:");
            for (int i = 0; i < n; i++) p.add(sc.nextLine().trim());
            G.put(A, p);
        }
        Map<Character, Set<Character>> FIRST = new LinkedHashMap<>();
        for (char A : nts.toCharArray()) FIRST.put(A, new LinkedHashSet<>());
        for (char A : nts.toCharArray()) calcFirst(A, G, FIRST);
        System.out.println("\nFirst Sets:");
        FIRST.forEach((A, s) -> System.out.println(A + " = " + s));
        
        Map<Character, Set<Character>> FOLLOW = new LinkedHashMap<>();
        for (char A : nts.toCharArray()) FOLLOW.put(A, new LinkedHashSet<>());
        FOLLOW.get(nts.charAt(0)).add('$');
        boolean change;
        do {
            change = false;
            for (char A : nts.toCharArray()) {
                for (String prod : G.get(A)) {
                    for (int i = 0; i < prod.length(); i++) {
                        char B = prod.charAt(i);
                        if (!FOLLOW.containsKey(B)) continue;
                        int before = FOLLOW.get(B).size();
                        if (i + 1 < prod.length()) {
                            char C = prod.charAt(i + 1);
                            if (FOLLOW.containsKey(C)) {
                                FIRST.get(C).forEach(c -> { if (c != EPS) FOLLOW.get(B).add(c); });
                                if (FIRST.get(C).contains(EPS))
                                    FOLLOW.get(B).addAll(FOLLOW.get(A));
                            } else {
                                FOLLOW.get(B).add(C);
                            }
                        } else {
                            FOLLOW.get(B).addAll(FOLLOW.get(A));
                        }
                        if (FOLLOW.get(B).size() > before) change = true;
                    }
                }
            }
        } while (change);
        System.out.println("\nFollow Sets:");
        FOLLOW.forEach((A, s) -> System.out.println(A + " = " + s));
    }

    static void calcFirst(char A, Map<Character, List<String>> G, Map<Character, Set<Character>> FIRST) {
        for (String prod : G.get(A)) {
            char a = prod.charAt(0);
            if (!G.containsKey(a)) 
                FIRST.get(A).add(a);
            else {
                if (FIRST.get(a).isEmpty()) calcFirst(a, G, FIRST);
                FIRST.get(A).addAll(FIRST.get(a));
            }
        }
    }
}