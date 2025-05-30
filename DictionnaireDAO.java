package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DictionnaireDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/dictio";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "root";

    public static MotResult rechercherMot(String mot) {
        MotResult motResult = new MotResult();
        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE)) {
            String query = "SELECT definition, wordtype FROM entries WHERE word=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, mot);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        motResult.setDefinition(resultSet.getString("definition"));
                        motResult.setType(resultSet.getString("wordtype"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motResult;
    }

    public static List<MotResult> afficherTousLesMots() {
        List<MotResult> mots = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE)) {
            String query = "SELECT word, definition, wordtype FROM entries";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        MotResult motResult = new MotResult();
                        motResult.setMot(resultSet.getString("word"));
                        motResult.setDefinition(resultSet.getString("definition"));
                        motResult.setType(resultSet.getString("wordtype"));
                        mots.add(motResult);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mots;
    }

    public static class MotResult {
        private String mot;
        private String definition;
        private String type;

        public String getMot() {
            return mot;
        }

        public void setMot(String mot) {
            this.mot = mot;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
