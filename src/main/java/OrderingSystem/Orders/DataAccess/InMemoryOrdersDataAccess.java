package OrderingSystem.Orders.DataAccess;

import OrderingSystem.Orders.Entities.IOrderComponent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryOrdersDataAccess implements IOrderDataAccess{
    static private Map<Integer, IOrderComponent>ordersDB;
    static private InMemoryOrdersDataAccess inMemoryOrdersDataAccessInstance = null;
    private InMemoryOrdersDataAccess(){
        ordersDB = new HashMap<>();
    }

    public static InMemoryOrdersDataAccess getInstance() {
        if(inMemoryOrdersDataAccessInstance == null){
            inMemoryOrdersDataAccessInstance = new InMemoryOrdersDataAccess();
        }
        return inMemoryOrdersDataAccessInstance;
    }

    @Override
    public IOrderComponent getOrderById(int orderId) {
        return ordersDB.get(orderId);
    }

    @Override
    public Collection<IOrderComponent> getCustomerOrders(String customerEmail) {
        return ordersDB.values().stream()
                .filter(order ->order.getAllOrderOwners().contains(customerEmail))
                .collect(Collectors.toList());
    }
}
