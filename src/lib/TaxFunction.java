package lib;

public class TaxFunction {

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     *
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     *
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     *
     */


    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        int tax = 0;

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }

        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);

        int netAnnualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible;

        tax = (int) Math.round(0.05 * (netAnnualIncome - nonTaxableIncome));

        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }

    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxableIncome = 54000000; // unmarried, no children

        if (isMarried) {
            nonTaxableIncome += 4500000; // married
        }

        if (numberOfChildren > 0) {
            int additionalChildren = Math.min(numberOfChildren, 3); // maximum of 3 children
            nonTaxableIncome += additionalChildren * 4500000; // per child
        }

        return nonTaxableIncome;
    }

}
