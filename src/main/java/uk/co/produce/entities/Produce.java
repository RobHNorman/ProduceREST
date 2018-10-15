package uk.co.produce.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Produce {

	private String name;
	private BigDecimal price;
	private Integer stock;
	private LocalDate updated;

	public Produce(String name, BigDecimal price, Integer stock, LocalDate updated) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.updated = updated;
	}
	public Produce(String name, BigDecimal price, Integer stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.updated = LocalDate.now();
	}
	public Produce() {
		this.updated = LocalDate.now();
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Produce)) {
            return false;
        }
        Produce produce = (Produce) o;
        return Objects.equals(name, produce.name) &&
                Objects.equals(price, produce.price) &&
                Objects.equals(stock, produce.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, stock);
    }
	
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Integer getStock() {
		return stock;
	}
	public String getUpdated() {
		return updated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
