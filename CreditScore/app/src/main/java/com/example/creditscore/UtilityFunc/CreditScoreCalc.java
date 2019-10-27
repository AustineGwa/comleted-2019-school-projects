package com.example.creditscore.UtilityFunc;

import com.example.creditscore.models.UserCreditData;

public class CreditScoreCalc {

    public  int  calculateCreditScore(UserCreditData userCreditData){

       int myCreditScore = 0;

        myCreditScore += getScoreFromSalary(userCreditData.getSalary());
        myCreditScore += getScoreFromData(userCreditData.getCurrentDebt());
        myCreditScore += getScoreFromSalary(userCreditData.getLastLoanPaid());
        myCreditScore += getScoreFromNumberOfLoans(userCreditData.getLoansPaid());


        return myCreditScore;
    }

    public int getScoreFromNumberOfLoans(double loansPaid){
        int score;
        if(loansPaid >= 200 ){
            score = 240;
        }else if( loansPaid >150){
            score = 200;
        }else if(loansPaid >100){
            score =180;
        }else if(loansPaid > 70){
            score = 130;
        }else{
            score = 75;
        }

        return score;
    }


    public int getScoreFromSalary(double salary){
        int score;
       if(salary >= 100000 ){
           score = 200;
       }else if( salary >60000){
           score = 150;
       }else if(salary>30000){
           score =100;
       }else if(salary > 10000){
           score = 50;
       }else{
           score = 25;
       }

       return score;
    }

    public int getScoreFromData(double debt){
        int score;
        if(debt >= 100000 ){
            score = 200;
        }else if( debt >60000){
            score = 150;
        }else if(debt>30000){
            score =100;
        }else if(debt > 10000){
            score = 50;
        }else{
            score = 25;
        }

        return score;
    }


    public String getRating(int score){

        String rating;

        if(score > 750){
            rating = "Excelent";
        }else if(score > 650){
            rating ="Great";
        }else if(score > 550){
            rating ="Good";
        }else if(score > 400){
            rating ="Average";
        }else if(score > 300){
            rating ="Poor";
        }else {
            rating ="Very poor";
        }

        return rating;
    }
}
