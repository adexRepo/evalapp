package com.kkp.evalapp.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kkp.evalapp.model.ReportCompetency;
import com.kkp.evalapp.model.ReportOverallEvaluation;
import com.kkp.evalapp.model.ReportWorkResult;

public class ReportDummy {
    private static final String[] indonesianNames = {
            "Afriza", "Afsana", "Aishiah", "Akia", "Akilah", "Albirru", "Aleena", "Aleeza", "Alila", "Alimah",
            "Aliya", "Allysa", "Anahita", "Anais", "Aneisha", "Annesa", "Aridhah", "Asfa", "Asfia", "Asheeqa",
            "Asia", "Asraa", "Atikah", "Aulianisa", "Aweeza", "Ayra", "Ayska", "Azifa", "Azkayra", "Azkha",
            "Azkia", "Azkira", "Azqila", "Badeea", "Badriya", "Baizah", "Basafa", "Bashair", "Bayu", "Bayzaa",
            "Bazla", "Bazriqa", "Bechara", "Berezira", "Bina", "Bizziza", "Busayna", "Cailey", "Calya", "Camilla",
            "Chaiza", "Chana", "Ciara", "Cyra", "Daarina", "Daiba", "Daizi", "Dalisha", "Daliya", "Dananir",
            "Daniyah", "Dhia", "Durriyyah", "Dzelila", "Dzemila", "Elina", "Elizah", "Elmeera", "Elmerya",
            "Hyiruma", "Lala", "Rina", "Riri"
    };

    public static List<ReportOverallEvaluation> generateDummyData(int numberOfData) {
        List<ReportOverallEvaluation> dummyDataList = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= numberOfData; i++) {
            ReportOverallEvaluation data = new ReportOverallEvaluation();
            data.setEvaluationId(i);
            data.setUserId(i);
            data.setUserName(indonesianNames[random.nextInt(indonesianNames.length)]);
            data.setEvaluator1Name(indonesianNames[random.nextInt(indonesianNames.length)]);
            data.setEvaluator2Name(indonesianNames[random.nextInt(indonesianNames.length)]);
            data.setOverallQuality(random.nextInt(5) + 1); // Random quality from 1 to 5
            DecimalFormat df = new DecimalFormat("#.00");
            String formattedNumber = df.format(30 + random.nextDouble() * 20);
            data.setOverallRating(formattedNumber); // Random rating from 30 to 50
            data.setCoachingResult("Tingkatkan lagi ketahanan nya");
            dummyDataList.add(data);
        }

        return dummyDataList;
    }

       private static final String[] DEPARTMENTS = {
            "Operational H.O - OAD",
            "Operational H.O - HRD",
            "Operational H.O - Legal/GRD",
            "Operational H.O - Accounting",
            "Operational H.O - Purchasing",
            "Operational H.O - Budget",
            "Operational H.O - Internal Audit",
            "Operational H.O - Treasury"
    };

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

    public static List<ReportWorkResult> generateDummyDataA() {
        List<ReportWorkResult> dummyDataList = new ArrayList<>();

        Random random = new Random();

        for (int i = 1; i <= 15; i++) {
            ReportWorkResult workResult = new ReportWorkResult();

            // Generate random department
            String department = DEPARTMENTS[random.nextInt(DEPARTMENTS.length)];
            workResult.setWorkResultDepartment(department);

            // Generate random double values with two decimal places
            double averageTarget = random.nextDouble() * 100;
            workResult.setWorkResultAverageTarget(Double.parseDouble(DECIMAL_FORMAT.format(averageTarget)));

            double averageRating = random.nextDouble() * 5;
            workResult.setWorkResultAverageRating(Double.parseDouble(DECIMAL_FORMAT.format(averageRating)));

            double averageResult = random.nextDouble() * 100;
            workResult.setWorkResultAverageResult(Double.parseDouble(DECIMAL_FORMAT.format(averageResult)));

            double averageQuality = random.nextDouble() * 100;
            workResult.setWorkResultAverageQuality(Double.parseDouble(DECIMAL_FORMAT.format(averageQuality)));

            dummyDataList.add(workResult);
        }

        return dummyDataList;
    }


     private static final String[] QUALITIES = {
            "Tidak Memenuhi Harapan",
            "Sebagian Besar Memenuhi Harapan",
            "Memenuhi Harapan",
            "Melampaui Harapan",
            "Sangat Melampaui Harapan"
    };

    private static final String[] RATINGS = {"1", "2", "3", "4", "5"};

    public static List<ReportCompetency> generateDummyDataC() {
        List<ReportCompetency> dummyDataList = new ArrayList<>();

        String[] names = {
                "Akia", "Akilah", "Albirru", "Aleena", "Aleeza", "Alila", "Alimah", "Aliya", "Allysa", "Anahita",
                "Anais", "Aneisha", "Annesa", "Aridhah", "Asfa", "Asfia", "Asheeqa", "Asraa", "Atikah", "Aulianisa",
                "Aweeza", "Ayra", "Ayska", "Azifa", "Azkayra", "Azkha", "Azkia", "Azkira", "Azqila", "Badeea",
                "Badriya", "Baizah"
        };

        Random random = new Random();

        for (int i = 1; i <= 15; i++) {
            ReportCompetency competency = new ReportCompetency();

            competency.setCompetencyId(i);
            competency.setCompetencyName(names[random.nextInt(names.length)]);
            competency.setCompetencyTarget("Target Value " + i);
            competency.setCompetencyRating(RATINGS[random.nextInt(RATINGS.length)]);
            competency.setCompetencyResult(random.nextInt(101) + "%");
            competency.setCompetencyQuality(QUALITIES[random.nextInt(QUALITIES.length)]);

            dummyDataList.add(competency);
        }

        return dummyDataList;
    }
}
