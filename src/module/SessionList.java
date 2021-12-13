package module;

import java.io.Serializable;
import java.util.ArrayList;

public class SessionList implements Serializable
{
  /** A class for storing all the sessions.
   * @param sessions An ArrayList containing all the sessions
   */
  private ArrayList<Session> sessions;

  /**
   * No-argument constructor initializing an empty ArrayList
   */
  public SessionList()
  {
    sessions = new ArrayList<>();
  }

  /**
   * Adds a session to the ArrayList.
   * @param session the sessions to be added.
   */
  public void addSession(Session session)
  {
    sessions.add(session);
  }

  /**
   * Removes a session from the ArrayList.
   * @param id the id of the session to be removed.
   */
  public void removeSession(String id)
  {
    for (int i = 0; i < sessions.size(); i++)
      if (sessions.get(i).getId().equals(id))
        sessions.remove(i);
  }

  /**
   * Gets the session by id from the sessions.
   * @param id the id of the wanted session.
   * @return the wanted Session object from the ArrayList.
   */
  public Session getSession(String id)
  {
    Session session = null;
    for (int i = 0; i < sessions.size(); i++)
      if (sessions.get(i).getId().equals(id))
        session = sessions.get(i);
    return session;
  }

  /**
   * Gets all the sessions from the ArrayList.
   * @return the ArrayList containing all the sessions.
   */
  public ArrayList<Session> getAllSessions()
  {
    return sessions;
  }

  /**
   * Gets all the sessions of a course.
   * @param id the id of the course.
   * @return the sessions of the course.
   */
  public ArrayList<Session> getAllSessionsByCourse(String id)
  {
    ArrayList<Session> foundSessions = new ArrayList<>();
    for (int i = 0; i < sessions.size(); i++)
    {
      if (sessions.get(i).getCourse().getId().equals(id))
        foundSessions.add(sessions.get(i));
    }
    return foundSessions;
  }

  /**
   * Gets the number of sessions.
   * @return the size of the ArrayList.
   */
  public int getSize()
  {
    return sessions.size();
  }

  /**
   * Converts SessionList into a String.
   * @return the String format
   */
  public String toString()
  {
    String temp = "";
    for (int i = 0; i < sessions.size(); i++)
    {
      temp += sessions.get(i);
    }
    return temp;
  }
}
