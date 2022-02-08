package br.com.analyzer.domain;

public class Analysis {
    private long quantityCustomer;
    private long quantitySalesman;
    private Sale saleMoreExpensive;
    private Salesman worstSeller;

    public void setQuantityCustomer(long quantityCustomer) {
        this.quantityCustomer = quantityCustomer;
    }

    public void setQuantitySalesman(long quantitySalesman) {
        this.quantitySalesman = quantitySalesman;
    }

    public void setSaleMoreExpensive(Sale saleMoreExpensive) {
        this.saleMoreExpensive = saleMoreExpensive;
    }

    public void setWorstSeller(Salesman worstSeller) {
        this.worstSeller = worstSeller;
    }

    public long getQuantityCustomer() {
        return quantityCustomer;
    }

    public long getQuantitySalesman() {
        return quantitySalesman;
    }

    public Sale getSaleMoreExpensive() {
        return saleMoreExpensive;
    }

    public Salesman getWorstSeller() {
        return worstSeller;
    }
}
