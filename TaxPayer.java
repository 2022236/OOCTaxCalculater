package taxcalculationsca;
/**
 *
 * @author lizandra 2022236 and Taciana 2022404
 */

public class TaxPayer {
    private float GrossPayment;
    private float NetPayment;
    private float USC;
    private float PRSCI;
    private float IcommingTax;
    
    private int totalWeekPerYear;
    private float weekPayment;
    private float basicTax;
    
    private float personalCredit;
    private float employeeCredit;
    
    
    /*private String name;;
    private double income;
    private double taxPaid;*/

    
    public TaxPayer(float GrossPayment) {
        this.GrossPayment = GrossPayment;
        this.personalCredit = 1775;
        this.employeeCredit = 1775;
        this.totalWeekPerYear = 52;
        this.weekPayment = this.GrossPayment/this.totalWeekPerYear;
        this.basicTax = (float) 0.2;
        
        
    }
    
    private float IcommingTaxCalculator(User user) {
        float credits = this.personalCredit;
        
        
        if(!user.getPosition().equals(new String(""))){
            credits += this.employeeCredit;
        }
        
        this.IcommingTax = (this.GrossPayment * this.basicTax) - credits;
        
        return this.IcommingTax;
    }
    
    private float USCTaxCalculator(User user) {
        float totalTax = 0;
        float leftValue = this.GrossPayment;
        
        if(this.GrossPayment > 13000) {
            leftValue -= 12012;
            totalTax += 12012 * 0.005;
        }
        
        if(leftValue >= 10908) {
            leftValue -= 10908;
            totalTax += 10908 * 0.02;
        }
        
        if(leftValue >= 47124) {
            leftValue -= 47124;
            totalTax += 47124 * 0.045;
        }
        
        if(leftValue > 0) {
            totalTax += leftValue * 0.08;
        }
        
        this.USC = totalTax;
        
        return this.USC;
    }
    
    private float PRSCI ( ){
        
    }
}
