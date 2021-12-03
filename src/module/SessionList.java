package module;

import java.util.ArrayList;

public class SessionList
{
  private ArrayList<Session> sessions;

  public SessionList()
  {
    sessions = new ArrayList<>();
  }

  public void addSession(Session session)
  {
    sessions.add(session);
  }

  public void removeSession(String id)
  {
    for (int i = 0; i < sessions.size(); i++)
      if (sessions.get(i).getId().equals(id))
        sessions.remove(i);
  }

  public Session getSession(String id)
  {
    Session session = null;
    for (int i = 0; i < sessions.size(); i++)
      if (sessions.get(i).getId().equals(id))
        session = sessions.get(i);
    return session;
  }

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

  public int getSize()
  {
    return sessions.size();
  }

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
