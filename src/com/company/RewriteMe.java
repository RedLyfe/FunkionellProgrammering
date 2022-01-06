package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();


    public int getAmountOfQuestionsInDatabase(){
        return (int) questions.stream().count();
    }

    public int getAmountOfQuestionsForACertainCategory(Category category){
      return (int) questions.stream().filter(s -> s.getCategory().equals(category)).count();
    }

    public List<String> getListOfAllQuestions(){
        return questions.stream().map(Question::getQuestionString).collect(Collectors.toList());

    }

    public List<String> getAllQuestionStringsBelongingACategory(Category category){
        return questions.stream().filter(s->s.getCategory().equals(category))
                .map(Question::getQuestionString)
                .collect(Collectors.toList());
    }

    public List<String> getAllAnswerOptionsDistinct(){
      return questions.stream().map(Question::getAllAnswers)
              .flatMap(Collection::stream)
              .distinct()
              .collect(Collectors.toList());
    }

    public boolean isThisAnAnswerOption(String answerCandidate){
        return questions.stream().map(Question::getAllAnswers).flatMap(Collection::stream).anyMatch(s -> s.equalsIgnoreCase(answerCandidate));

    }

    public int getAnswerCandidateFrequncy(String answerCandidate){
       return (int) questions.stream().map(Question::getAllAnswers)
               .flatMap(Collection::stream)
               .filter(s -> s.equalsIgnoreCase(answerCandidate)).count();
    }

    public Map<Category, List<String>> getQuestionGroupedByCategory(){
      return questions.stream()
              .collect(Collectors
              .groupingBy(Question::getCategory,Collectors.mapping(Question::getQuestionString,Collectors.toList())));

    }

    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        return questions.stream()
                .filter(s->s.getCategory().equals(c))
                .flatMap(q -> q.getAllAnswers().stream())
                .reduce("", (u, e) -> e.length() > u.length() ? e : u);
    }

    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();

    }
}
