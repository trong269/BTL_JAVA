package bankapp.guis;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFrame;
import java.util.ArrayList;
import objs.Transaction;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

/**
 * Lớp SpendingChart tạo biểu đồ thống kê chi tiêu dựa trên danh sách giao dịch.
 */
public class SpendingChart extends JFrame {
    public SpendingChart(ArrayList<Transaction> transactions) {
        setTitle("Biểu Đồ Thống Kê Chi Tiêu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = createDataset(transactions);

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createLineChart(
                "Biến Động Số Dư",
                "Ngày",
                "Số Dư (VND)",
                dataset
        );

        // Thêm biểu đồ vào panel
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    /**
     * Tạo dataset từ danh sách giao dịch.
     *
     * @param transactions Danh sách giao dịch
     * @return DefaultCategoryDataset
     */
    private DefaultCategoryDataset createDataset(ArrayList<Transaction> transactions) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            balance = balance.add(transaction.getTrans_amount());
            String date = sdf.format(transaction.getTrans_date());
            dataset.addValue(balance, "Số Dư", date);
        }

        return dataset;
    }
}
