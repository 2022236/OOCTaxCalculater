package OOCTaxCalculater;

/**
 *
 * @author Lizandra Matos 2022336 and Taciana Oliveira 2022404
 */
public class TaxPayer { //variables

    private final float GrossPayment;
    private float NetPayment;
    private float USC;
    private float PRSCI;
    private float IcommingTax;

    private int totalWeekPerYear;
    private float weekPayment;
    private float basicTax;

    private final float personalCredit;
    private final float employeeCredit;

    public TaxPayer(float GrossPayment) {
        this.GrossPayment = GrossPayment;
        this.personalCredit = 1775;
        this.employeeCredit = 1775;
        this.totalWeekPerYear = 52;
        this.weekPayment = this.GrossPayment / this.totalWeekPerYear;
        this.basicTax = (float) 0.2;
        
        this.NetPayment = -1;
        this.USC = -1;
        this.PRSCI = -1;
        this.IcommingTax = -1;

}

public TaxPayer(float GrossPayment, float NetPayment, float USC, float PRSCI, float IcommingTax) {
        this.GrossPayment = GrossPayment;
        this.personalCredit = 1775;
        this.employeeCredit = 1775;
        this.totalWeekPerYear = 52;
        this.weekPayment = this.GrossPayment/this.totalWeekPerYear;
        this.basicTax = (float) 0.2;
        this.NetPayment = NetPayment;
        this.USC = USC;
        this.PRSCI = PRSCI;
        this.IcommingTax = IcommingTax;        
 
    }
    
    public TaxPayer() {
        this.GrossPayment = 0;
        this.personalCredit = 1775;
        this.employeeCredit = 1775;
        this.totalWeekPerYear = 52;
        this.weekPayment = this.GrossPayment / this.totalWeekPerYear;
        this.basicTax = (float) 0.2;

        this.NetPayment = -1;
        this.USC = -1;
        this.PRSCI = -1;
        this.IcommingTax = -1;

    }
    
    protected float _IcommingTaxCalculator(String position ) {
        float credits = this.personalCredit;  // Initializes the credits variable with the personal credit value
        
        // Checks if the position is not an empty string
        if(!position.equals("")){ // If the position is not empty, adds the employee credit to the total credits
            credits += this.employeeCredit;
        }
        // Calculates the incoming tax based on gross payment, basic tax, and total credits
        this.IcommingTax = (this.GrossPayment * this.basicTax) - credits;
        
        return this.IcommingTax;  // Returns the calculated incoming tax
    }
    
    public float USCTaxCalculator() {
       // Initializing variables
        float totalTax = 0;
        float leftValue = this.GrossPayment;
        
        // Checking if GrossPayment exceeds 13000
        if(this.GrossPayment > 13000) {
            leftValue -= 12012;
            totalTax += 12012 * 0.005;
        }
        // Checking if leftValue is greater than or equal to 10908
        if(leftValue >= 10908) {
            leftValue -= 10908;
            totalTax += 10908 * 0.02;
        }
        // Checking if leftValue is greater than or equal to 47124
        if(leftValue >= 47124) {
            leftValue -= 47124;
            totalTax += 47124 * 0.045;
        }
        // Calculating the tax for the remaining leftValue
        if(leftValue > 0) {
            totalTax += leftValue * 0.08;
        }
        
        this.USC = totalTax; // Assigning the total USC calculated to the USC variable
        
        return this.USC; // Returning the USC value
    }
    
    public float PRSCI ( ){
        // Initializing variables
        float taxPRSIcredit = 0;
        float taxPRSIdiscount = 0;
            // Checking conditions based on weekPayment
            if(this.weekPayment <= 424) {
                
                taxPRSIcredit += (this.GrossPayment - 352.01) / 6;
                
                if(this.weekPayment >= 352.01 && this.weekPayment <= 424) {
                    if(taxPRSIcredit > 12) {
                        taxPRSIcredit = 12;
                    }
                }
                
            }
            else if(this.weekPayment > 424){
                taxPRSIcredit = 12;
            }
        
        // Calculating taxPRSIdiscount based on taxPRSIcredit and weekPayment
        if(taxPRSIcredit > 0) {
            taxPRSIdiscount = (float) ((0.04 * this.weekPayment) - taxPRSIcredit);
        } else if(this.weekPayment <= 352) {
            taxPRSIdiscount = (float) 0.0;
        }
        
        this.PRSCI = taxPRSIdiscount * this.totalWeekPerYear;// Calculating the total PRSI for the entire year
        
        return this.PRSCI; // Returning the calculated PRSI
    }
    
    protected float _NetPaymentCalculator(String position) {
        float totalCredit  = this.personalCredit; // Initializing totalCredit with personalCredit
        
        if(!position.equals(new String(""))) { // Checking if position is not an empty string to include employeeCredit
            totalCredit += this.employeeCredit;
        }
        // Calculating the NetPayment after deducting taxes (USC, PRSCI, IcommingTax) and adding totalCredit
        this.NetPayment = this.GrossPayment - (this.USC + this.PRSCI + this.IcommingTax) + totalCredit;
        
        return this.NetPayment;  // Returning the calculated NetPayment
    }

    protected float getGrossPayment() {
        return this.GrossPayment;
    }

    protected float getNetPayment() {
        if(this.NetPayment != -1){
            return this.NetPayment;
        } else {
            return -1;
        }
    }

    protected float getUSC() {
        if(this.USC != -1){
             return this.USC;
        } else {
            return -1;
        }
        
    }

    protected float getPRSCI() {
        if(this.PRSCI != -1){
            return this.PRSCI;
        } else {
            return -1;
        }
    }

    protected float getIcommingTax() { 
        if(this.IcommingTax != -1){
            return IcommingTax;
        } else {
            return -1;
        }
    }

  
    
    
}
