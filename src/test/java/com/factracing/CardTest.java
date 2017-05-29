package com.factracing;


import com.factracing.beans.Difficulty;
import org.junit.Before;
import org.junit.Test;

import com.factracing.beans.Card;

import static org.junit.Assert.assertEquals;


public class CardTest
{
    private static int DIFFICULTY = 2;
    private static String QUESTION = "Where", CORRECT_ANSWER = "There";
    private static String[] ANSWERS = {"Somewhere", "Nowhere", "Everywhere"};
    private Card emptyCard = new Card("", new String[0], "", 0);
    private Card simpleCard = new Card(QUESTION, ANSWERS, CORRECT_ANSWER, DIFFICULTY);

	@Test
    public void difficultyTest(){
        assertEquals(0, emptyCard.getDifficulty());
        assertEquals(DIFFICULTY, simpleCard.getDifficulty());
    }

    @Test
    public void questionTest(){
        assertEquals("", emptyCard.getQuestion());
        assertEquals(QUESTION, simpleCard.getQuestion());
    }

    @Test
    public void answersTest(){
        assertEquals(new String[0], emptyCard.getAnswers());
        assertEquals(ANSWERS, simpleCard.getAnswers());
    }

    @Test
    public void correctAnserTest(){
        assertEquals("", emptyCard.getCorrectAnswer());
        assertEquals(CORRECT_ANSWER, simpleCard.getCorrectAnswer());
    }
}