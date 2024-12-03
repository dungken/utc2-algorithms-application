import java.util.PriorityQueue;

class Order {
    int price;
    int quantity;
    boolean isBuyOrder; // true: lệnh mua, false: lệnh bán

    // Constructor
    public Order(int price, int quantity, boolean isBuyOrder) {
        this.price = price;
        this.quantity = quantity;
        this.isBuyOrder = isBuyOrder;
    }

    @Override
    public String toString() {
        return (isBuyOrder ? "Lệnh mua" : "Lệnh bán") + ": " + quantity + " cổ phiếu tại giá " + price;
    }
}

public class StockMarket {
    PriorityQueue<Order> buyOrders;
    PriorityQueue<Order> sellOrders;

    // Constructor
    public StockMarket() {
        buyOrders = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);

        sellOrders = new PriorityQueue<>((o1, o2) -> o1.price - o2.price);
    }

    // Phương thức để đặt lệnh
    public void placeOrder(Order order) {
        if (order.isBuyOrder) {
            buyOrders.offer(order);
        } else {
            sellOrders.offer(order);
        }
        matchOrders();
    }

    private void matchOrders() {
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            Order buy = buyOrders.peek();
            Order sell = sellOrders.peek();

            if (buy.price >= sell.price) {
                int quantity = Math.min(buy.quantity, sell.quantity);
                System.out.println("Thực hiện giao dịch: " + quantity + " cổ phiếu tại giá " + sell.price);

                buy.quantity -= quantity;
                sell.quantity -= quantity;

                if (buy.quantity == 0) buyOrders.poll();
                if (sell.quantity == 0) sellOrders.poll();
            } else {
                break;
            }
        }
    }

    // Test
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        market.placeOrder(new Order(100, 50, true));  // Lệnh mua 50 cổ phiếu tại giá 100
        market.placeOrder(new Order(90, 20, false));  // Lệnh bán 20 cổ phiếu tại giá 90
        market.placeOrder(new Order(95, 30, false));  // Lệnh bán 30 cổ phiếu tại giá 95
        market.placeOrder(new Order(85, 40, true));   // Lệnh mua 40 cổ phiếu tại giá 85
    }
}
