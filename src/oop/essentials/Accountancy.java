package oop.essentials;

import java.math.BigDecimal;

import oop.Decimal;
import oop.Order;
import oop.Packaging;
import oop.Percentage;
import oop.Pricing;
import oop.Product;

public class Accountancy {

	public static Decimal calculateUnitAmount(Decimal unit_price, Percentage discount) {
		return new Decimal(
				unit_price.toBigDecimal().subtract(
						unit_price.toBigDecimal().multiply(
								discount.toBigDecimal()
						)
				)
		);
	}
	public static Decimal calculateNetAmount(Packaging packaging, Pricing pricing) {
		int quantity = packaging.getQty().getQuantity();
		
		Decimal 
		unit_amount = calculateUnitAmount(pricing.getUnitPrice(), pricing.getDiscount()),
		net_amount = new Decimal(unit_amount.toBigDecimal().multiply(new BigDecimal(quantity)));
		
		return net_amount;
	}
	public static Decimal calculateNetAmount(Product product) {
		return calculateNetAmount(product.getPackaging(), product.getPricing());
	}	
	public static Decimal calculateNetAmount(Order order) {
		return calculateNetAmount(order.getProduct());
	}
	public static Decimal calculateCostAmount(Order orders[]) {
		Decimal	cost, total_amount = new Decimal();
		BigDecimal qty;
		for(Order order: orders) {
			cost = order.getProduct().getPricing().getCost();
			qty = new BigDecimal(order.getProduct().getPackaging().getQty().getQuantity());
			
			total_amount = total_amount.add(cost.multiply(new Decimal(qty)));
		}
		return total_amount;
	}
	public static Decimal calculateTotalNetAmount(Order orders[]) {
		Decimal total_netAmount = new Decimal();
		for(Order order: orders) {
			total_netAmount = total_netAmount.add(order.getNetAmount());
		}
		return total_netAmount;
	}
	public static Decimal calculateProfit(Decimal total_net_amount, Decimal cost_amount) {
		return total_net_amount.subtract(cost_amount);
	}
}


