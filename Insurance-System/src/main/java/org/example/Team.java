package org.example;

import org.example.domain.insurance.Insurance;

/**
 * @author yoon
 * @version 1.0
 *
 */
public abstract class Team {

    private int team_Id;
    private String team_name;
    public Insurance m_Insurance;

    public Team() {

    }

    public void finalize() throws Throwable {

    }

    public abstract void manage();

    public abstract void process(int processNum, int domainId);

    public abstract void register();

    public abstract void remove();

    public abstract Object retrieve();

}