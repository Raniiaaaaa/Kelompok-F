import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        if (loginMenu()) {

            boolean status = true;
            while (status) {
                menu();
                System.out.println("\nApakah ada pemesan lagi? Y/N");
                char cekStatus = input.next().charAt(0);
                status = cekStatus == 'Y' ? true : false;
            }
        } else {
            System.exit(0);
        }
    }

    private static boolean loginMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Username : ");
        String username = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();
        if (isValid(username, password)) {
            System.out.println("Login Berhasil");
            return true;
        } else {
            System.out.println("Login Gagal");
            return false;
        }
    }

    private static boolean isValid(String username, String password) {
        if (username.equals("Kelompok F") && password.equals("FFFFFF")) {
            return true;
        } else {
            return false;
        }
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("SELAMAT DATANG DI JATIMPARK 6 SOFTWARE");
        System.out.println("1. Mulai proses pembayaran");
        System.out.println("2. Keluar");
        System.out.print("PILIHAN : ");
        int pilih = input.nextInt();

        switch (pilih) {
            case 1:
                String hasil[][] = inputData();
                cetakStruk(hasil.length, hasil);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak ditemukan");
                break;
        }
    }

    public static String[][] inputData() {
        Scanner input = new Scanner(System.in);
        System.out.printf("\n||=========================================================||");
        System.out.printf("\n|| NO  |     KATEGORI       |    Weekdays   |    Weekends  ||");
        System.out.printf("\n|| 1   |     DEWASA         |    Rp40000    |    Rp50000   ||");
        System.out.printf("\n|| 2   |     Anak Kecil     |    Rp28000    |    Rp35000   ||");
        System.out.printf("\n||=========================================================||");
        System.out.print("\nMasukkan Berapa Orang : ");
        int berapaOrang = input.nextInt();

        int umur, kapan; // 0 -> weekdays, 1 -> weekends

        kapan = hariBinarizer();

        String[][] tiket = new String[berapaOrang][2];

        for (int i = 0; i < berapaOrang; i++) {
            System.out.print("Masukkan Umur      : ");
            umur = input.nextInt();

            tiket[i][0] = converterID(umur, kapan);
            tiket[i][1] = String.valueOf(penghitungHarga(umur, kapan));
        }

        return tiket;
    }

    public static int hariBinarizer() {
        Scanner input = new Scanner(System.in);
        System.out.print("""
                1. Senin
                2. Selasa
                3. Rabu
                4. Kamis
                5. Jumat
                6. Sabtu
                7. Minggu
                Hari apa ?
                """);

        int hari = input.nextInt();

        if (hari <= 5 && hari > 0) {
            return 0;
        } else if (hari == 6 || hari == 7) {
            return 1;
        }
        return -1;
    }

    private static int penghitungHarga(int umur, int kapan) {
        int harga = 0;
        if (umur < 17) {
            harga = 3500;
        } else if (umur >= 17) {
            harga = 5000;
        }
        if (kapan == 0) {
            harga *= 8;
        } else if (kapan == 1) {
            harga *= 10;
        }
        return harga;
    }

    public static String converterID(int usia, int hari) {
        String ID = "";

        String cekUsia = usia < 17 ? "BCL-" : "DWS-";
        ID += cekUsia;

        String cekHari = hari == 0 ? "DAY-" : "END-";
        ID += cekHari;

        return ID;
    }

    public static void cetakStruk(int jumlahTiket, String[][] idTiket) {
        int totalHarga = 0;
        System.out.printf("\n      STRUK PEMBAYARAN JATIMPARK 6     ");

        System.out.printf("\n      Untuk %d orang", jumlahTiket);
        System.out.printf("\n||====================================||");
        System.out.printf("\n|| NO  |     ID TIKET    | Harga (Rp) ||");
        System.out.printf("\n||====================================||");
        for (int i = 0; i < jumlahTiket; i++) {
            System.out.printf("\n||%4d |   %s%03d   |  %8s  ||", i + 1, idTiket[i][0], i + 1,
                    idTiket[i][1]);
            totalHarga += Integer.parseInt(idTiket[i][1]);
        }
        System.out.printf("\n||====================================||");
        System.out.printf("\n||                 TOTAL |  %8s  ||", +totalHarga);
        System.out.printf("\n||====================================||");

    }
}
