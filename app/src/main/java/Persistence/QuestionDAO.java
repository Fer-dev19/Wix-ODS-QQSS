package Persistence;


import BusinessLogic.Question;

public class QuestionDAO extends DBA<Question> {
    public QuestionDAO(){
        init(Question.class);
    }
}
