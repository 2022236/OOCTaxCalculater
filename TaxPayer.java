package OOCTaxCalculater;

/**
 *
 * @author Lizandra Matos 2022336 and Taciana Oliveira 2022404
 */
public class TaxPayer { // Class representing taxpayer financial information
    
    // Variables to store financial details
    private final float GrossPayment; // Gross payment amount (cannot be changed after initialization)
    private float NetPayment; // Net payment amount
    private float USC; // Universal Social Charge
    private float PRSCI; // Pay Related Social Insurance
    private float IcommingTax; // Incoming Tax
    
    // Other financial variables
    private int totalWeekPerYear; // Total weeks in a year
    private float weekPayment; // Payment per week
    private float basicTax; // Basic tax rate
    
    // Constants for tax calculation
    private final float personalCredit; // Personal credit
    private final float employeeCredit; // Employee credit

    // Constructor to initialize taxpayer's financial details
    public TaxPayer(float GrossPayment) {
         // Initializing variables
        this.GrossPayment = GrossPayment; // Assigning GrossPayment value provided in the constructor
        this.personalCredit = 1775; // Setting a fixed value for personal credit
        this.employeeCredit = 1775; // Setting a fixed value for employee credit
        this.totalWeekPerYear = 52; // Total weeks per year
        this.weekPayment = this.GrossPayment / this.totalWeekPerYear; // Calculating payment per week
        this.basicTax = (float) 0.2; // Setting the basic tax rate (20%)

        // Initializing calculated payments and taxes to -1
        this.NetPayment = -1;
        this.USC = -1;
        this.PRSCI = -1;
        this.IcommingTax = -1;

}

public TaxPayer(float GrossPayment, float NetPayment, float USC, float PRSCI, float IcommingTax) {
        // Assigning values passed to the constructor to respective class fields
        this.GrossPayment = GrossPayment; // Assigning GrossPayment value provided in the constructor
        this.personalCredit = 1775; // Setting a fixed value for personal credit
        this.employeeCredit = 1775; // Setting a fixed value for employee credit
        this.totalWeekPerYear = 52; // Total weeks per year
        this.weekPayment = this.GrossPayment/this.totalWeekPerYear; // Calculating payment per week
        this.basicTax = (float) 0.2; // Setting the basic tax rate (20%)
        this.NetPayment = NetPayment; // Assigning NetPayment value provided in the constructor
        this.USC = USC; // Assigning USC value provided in the constructor
        this.PRSCI = PRSCI; // Assigning PRSCI value provided in the constructor
        this.IcommingTax = IcommingTax; // Assigning IcommingTax value provided in the constructor       
 
    }

    // This constructor initializes a TaxPayer object with default values:
    public TaxPayer() {
        this.GrossPayment = 0;
        this.personalCredit = 1775;
        this.employeeCredit = 1775;
        this.totalWeekPerYear = 52; // - totalWeekPerYear is set to 52 weeks.
        this.weekPayment = this.GrossPayment / this.totalWeekPerYear; // - weekPayment is calculated based on GrossPayment divided by totalWeekPerYear.
        this.basicTax = (float) 0.2; // - basicTax is set to 0.2 (20%).

        this.NetPayment = -1;
        this.USC = -1;
        this.PRSCI = -1;
        this.IcommingTax = -1;

    }

    // This method, '_IcommingTaxCalculator', calculates the incoming tax based on the provided 'position' and existing financial attributes.
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
