
package dk.error404.model;

import java.io.Serializable;

public class TeamParticipant
    implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String userId;
    private int teamId;
    private String role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
