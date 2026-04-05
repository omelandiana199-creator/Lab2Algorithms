import java.util.*;

public class Algorithms {

    public static void main(String[] args) {
        int N = 10000;
        int[] data = new Random().ints(N, 0, N).toArray();

        System.out.println(" ЧАСТИНА 1: Пошук дублікатів ");
        System.out.println("Метод | Час (нс) | Пам'ять (байт)");

        // Рівень 1: Наївний
        measure("Level 1 (Naive)", () -> {
            for (int i = 0; i < data.length; i++) {
                for (int j = i + 1; j < data.length; j++) {
                    if (data[i] == data[j]) return;
                }
            }
        });

        // Рівень 2: Швидкий
        measure("Level 2 (Space-heavy)", () -> {
            boolean[] seen = new boolean[N + 1];
            for (int x : data) {
                if (seen[x]) return;
                seen[x] = true;
            }
        });

        // Рівень 3: Оптимальний
        measure("Level 3 (Balanced)", () -> {
            int[] sorted = data.clone();
            Arrays.sort(sorted);
            for (int i = 0; i < sorted.length - 1; i++) {
                if (sorted[i] == sorted[i+1]) return;
            }
        });

       System.out.println("\n ЧАСТИНА 2: Хешування");
       
        String Word = "кіт";
        String Word2 = "тік"; 
        
        int M = 997; 
        int hash = calculateHash(Word, M);
        int hash2 = calculateHash(Word2, M);
        
        System.out.println("Хеш для слова '" + Word + "': " + hash);
        System.out.println("Хеш для слова '" + Word2 + "': " + hash2);
    }


    public static void measure(String label, Runnable task) {
        System.gc(); // Виклик збирача сміття [cite: 112]
        Runtime runtime = Runtime.getRuntime();
        long memBefore = runtime.totalMemory() - runtime.freeMemory();

        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();

        long memAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(label + " | " + (endTime - startTime) + " | " + (memAfter - memBefore));
    }

    // Хешування (Варіант 5: Сума з урахуванням позиції) [cite: 201, 203]
    public static int calculateHash(String s, int M) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + (long) s.charAt(i) * (i + 1)) % M;
        }
        return (int) hash;
    }
}
