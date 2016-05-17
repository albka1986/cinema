package com.cinema.datasource;

import com.cinema.ReadProperties;
import com.cinema.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public final class DataSource {


    private static String driverName = ReadProperties.readProperties().getProperty("driverName");
    private static String url = ReadProperties.readProperties().getProperty("url");
    private static String user = ReadProperties.readProperties().getProperty("user");
    private static String password = ReadProperties.readProperties().getProperty("password");

    private static final String CHECK_LOGIN_EQUALS = "SELECT * FROM users WHERE login=?;";
    private static final String CREATE_SESSION = "INSERT INTO sessions (sessionDate, id_movie) VALUES (?,?)";
    private static final String FIND_ALL_SESSIONS = "SELECT * FROM sessions ORDER BY(id_movie);";
    private static final String FIND_SESSION_BY_MOVIE_ID = "SELECT * FROM sessions WHERE id_movie =";
    private static final String FIND_SESSION_BY_ID_SESSION = "SELECT * FROM sessions WHERE id=";
    private static final String DELETE_SESSION_BY_ID = "DELETE FROM sessions WHERE id =?;";
    private static final String CREATE_USER = "INSERT INTO users (name, surname, login, password, birthday, role, email) VALUES (?,?,?,?,?,?,?)";
    private static final String FIND_ALL_USERS = "SELECT * FROM db_cinema.users;";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login=? and password=?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id= ";
    private static final String CREATE_MOVIE = "INSERT INTO movies (title, description, duration) VALUES (?,?,?) ";
    private static final String DELETE_MOVIE_BY_ID = "DELETE FROM movies WHERE id=?";
    private static final String FIND_ALL_MOVIES = "SELECT * FROM db_cinema.movies;";
    private static final String FIND_MOVIE_BY_ID = "SELECT * FROM movies WHERE id =";
    private static final String FIND_MOVIE_BY_TITLE = "SELECT * FROM movies WHERE title =";
    private static final String CREATE_TABLE_TICKETS = "(`column_1` TINYINT NOT NULL DEFAULT '0'," +
            "  `column_2` TINYINT NULL DEFAULT '0'," +
            "  `column_3` TINYINT NULL DEFAULT '0'," +
            "  `column_4` TINYINT NULL DEFAULT '0'," +
            "  `column_5` TINYINT NULL DEFAULT '0'," +
            "  `column_6` TINYINT NULL DEFAULT '0'," +
            "  `column_7` TINYINT NULL DEFAULT '0'," +
            "  `column_8` TINYINT NULL DEFAULT '0'," +
            "  `column_9` TINYINT NULL DEFAULT '0'," +
            "  `column_10` TINYINT NULL DEFAULT '0'," +
            "  `column_11` TINYINT NULL DEFAULT '0'," +
            "  `column_12` TINYINT NULL DEFAULT '0'," +
            "  `column_13` TINYINT NULL DEFAULT '0'," +
            "  `column_14` TINYINT NULL DEFAULT '0'," +
            "  `column_15` TINYINT NULL DEFAULT '0'," +
            "  `row` INT NOT NULL AUTO_INCREMENT," +
            "  `id_session` INT NULL," +
            "  PRIMARY KEY (`row`)," +
            "  INDEX `id_session_idx` (`id_session` ASC)," +
            "    FOREIGN KEY (`id_session`)" +
            "    REFERENCES `db_cinema`.`sessions` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION);";
    private static final String CREATE_TABLE_ALL_TICKETS = "CREATE TABLE IF NOT EXISTS `db_cinema`.`alltickets` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `row` INT NOT NULL," +
            "  `column` INT NOT NULL," +
            "  `id_user` INT NOT NULL," +
            "  `id_session` INT NOT NULL," +
            "  PRIMARY KEY (`id`)," +
//            "  CONSTRAINT `id_user`" +
            "    FOREIGN KEY (`id`)" +
            "    REFERENCES `db_cinema`.`users` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION," +
//            "  CONSTRAINT `id_session`" +
            "    FOREIGN KEY (`id`)" +
            "    REFERENCES `db_cinema`.`sessions` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION);";
    private static final String INSERT_TICKET_IN_ALLTICKETS = "INSERT INTO `alltickets` (row, `column`, id_user, id_session) VALUES (?,?,?,?)";
    private static final String FIND_TICKETS_BY_USER_ID = "SELECT * FROM alltickets WHERE id_user=?";

    private static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS db_cinema";
    private static final String CREATE_TABLE_MOVIES = "CREATE TABLE IF NOT EXISTS  `db_cinema`.`movies` (" +
            "  `id` INT(11) NOT NULL AUTO_INCREMENT," +
            "  `title` VARCHAR(45) NOT NULL," +
            "  `description` VARCHAR(500) NULL," +
            "  `duration` INT(11) NULL," +
            "  PRIMARY KEY (`id`))" +
            "ENGINE = InnoDB" +
            "DEFAULT CHARACTER SET = utf8;";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS `db_cinema`.`users` (" +
            "  `id` INT(11) NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  `surname` VARCHAR(45) NOT NULL," +
            "  `login` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  `birthday` DATE NOT NULL," +
            "  `role` VARCHAR(45) NOT NULL DEFAULT 'USER'," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))" +
            "ENGINE = InnoDB" +
            "DEFAULT CHARACTER SET = utf8;";
    private static final String CREATE_TABLE_SESSIONS = "CREATE TABLE IF NOT EXISTS `db_cinema`.`sessions` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `sessionDate` DATETIME NOT NULL," +
            "  `id_movie` INT NULL," +
            "  `id_tickets` INT NULL," +
            "  PRIMARY KEY (`id`)," +
            "  INDEX `id_movie` (`id_movie` ASC)," +
            "  CONSTRAINT `id_movie`" +
            "    FOREIGN KEY (`id_movie`)" +
            "    REFERENCES `db_cinema`.`movies` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)" +
            "ENGINE = InnoDB" +
            "DEFAULT CHARACTER SET = utf8mb4;";

    public static synchronized Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void createDatabase() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = conn.createStatement();
            statement.execute(CREATE_DATABASE);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createMainsTablesOnMysql() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE_MOVIES);
            statement.execute(CREATE_TABLE_USERS);
            statement.execute(CREATE_TABLE_SESSIONS);
            statement.execute(CREATE_TABLE_ALL_TICKETS);
            statement.close();
            if (!checkLoginToEquals("admin")) {
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
                preparedStatement.setString(1, "admin");
                preparedStatement.setString(2, "admin");
                preparedStatement.setString(3, "admin");
                preparedStatement.setString(4, "admin");
                preparedStatement.setString(5, String.valueOf(LocalDate.now()));
                preparedStatement.setString(6, "ADMIN");
                preparedStatement.setString(7, "");
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(User user) {

        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getBirthday()));
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.setString(7, user.getEmail());

            preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> findAllUsers() {
        List<User> users = new LinkedList<>();
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setBirthday(LocalDate.parse(resultSet.getString(6)));
                user.setRole(Role.valueOf(resultSet.getString(7)));
                user.setEmail(resultSet.getString(8));
                users.add(user);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static User findUserByLoginAndPassword(String login, String password) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setLogin(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setBirthday(LocalDate.parse(resultSet.getString(6)));
            user.setRole(Role.valueOf(resultSet.getString(7)));
            user.setEmail(resultSet.getString(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User findUserById(Integer idUser) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setLogin(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setBirthday(LocalDate.parse(resultSet.getString(6)));
            user.setRole(Role.valueOf(resultSet.getString(7)));
            user.setEmail(resultSet.getString(8));

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void deleteUserById(int id) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(DELETE_USER_BY_ID + id);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createMovie(Movie movie) {

        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MOVIE);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDescription());
            preparedStatement.setInt(3, movie.getDuration());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMovieById(Integer idMovie) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOVIE_BY_ID);
            preparedStatement.setInt(1, idMovie);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> findAllMovies() {
        List<Movie> movies = null;

        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_MOVIES);

            movies = new LinkedList<>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setDuration(resultSet.getInt(4));
                movies.add(movie);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static Movie findMovieById(Integer id) {
        Movie movie = new Movie();
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_MOVIE_BY_ID + id + ";");
            resultSet.next();
            movie.setId(resultSet.getInt(1));
            movie.setTitle(resultSet.getString(2));
            movie.setDescription(resultSet.getString(3));
            movie.setDuration(resultSet.getInt(4));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public static Movie findMovieByTitle(String title) {
        Movie movie = new Movie();
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_MOVIE_BY_TITLE + "\"" + title + "\"" + ";");
            resultSet.next();
            movie.setId(resultSet.getInt(1));
            movie.setTitle(resultSet.getString(2));
            movie.setDescription(resultSet.getString(3));
            movie.setDuration(resultSet.getInt(4));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public static void createSession(Session session) {
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SESSION);
            preparedStatement.setString(1, String.valueOf(session.getSessionDate()));
            preparedStatement.setInt(2, session.getMovie().getId());
            preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sessions");
            resultSet.last();
            int id_tickets = resultSet.getInt(1);
            PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `db_cinema`.`sessions` SET `id_tickets`='" + id_tickets + "' WHERE `id`='" + resultSet.getInt(1) + "';");
            preparedStatement2.executeUpdate();
            preparedStatement.close();

            createMysqlTable(id_tickets, connection);
            insertRowsIntoMySqlTable(id_tickets, connection);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Session findSessionById(Integer idSession) {
        Session session = null;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_SESSION_BY_ID_SESSION + idSession);
            if (resultSet.next()) {
                session = new Session();
                session.setId(resultSet.getInt(1));
                session.setSessionDate(LocalDateTime.parse(resultSet.getString(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
                session.setMovie(findMovieById(resultSet.getInt(3)));
                session.setTickets(findTicketsBySessionId(resultSet.getInt(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    public static void deleteSessionById(Integer idSession) {
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();
            PreparedStatement ps = connection.prepareStatement(DELETE_SESSION_BY_ID);
            ps.setInt(1, idSession);
            ps.executeUpdate();
            ps.close();
            Statement stmt2 = connection.createStatement();
            stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
            stmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createMysqlTable(Integer id_tickets, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE tickets_" + id_tickets + CREATE_TABLE_TICKETS);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertRowsIntoMySqlTable(Integer id_tickets, Connection connection) {
        try {
            for (int i = 0, row = 1; i < Hall.getRows(); i++, row++) {
                PreparedStatement insertRows = connection.prepareStatement("INSERT INTO tickets_" + id_tickets + " (`row`) VALUES (?)");
                insertRows.setInt(1, row);
                insertRows.close();

                PreparedStatement insertIdSession = connection.prepareStatement("INSERT INTO tickets_" + id_tickets + " (`id_session`) VALUES (?)");
                insertIdSession.setInt(1, id_tickets);
                insertIdSession.executeUpdate();
                insertIdSession.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Session> findAllSessions() {
        List<Session> sessions = new LinkedList<>();
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SESSIONS);

            while (resultSet.next()) {

                Session session = new Session();
                session.setId(resultSet.getInt(1));
                session.setSessionDate(LocalDateTime.parse(resultSet.getString(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
                session.setMovie(findMovieById(resultSet.getInt(3)));
                sessions.add(session);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    public static List<Session> findSessionsByMovieId(Integer movieId) {
        List<Session> sessions = new LinkedList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_SESSION_BY_MOVIE_ID + movieId + ";");
            while (resultSet.next()) {
                Session session = new Session();
                session.setId(resultSet.getInt(1));
                session.setSessionDate(LocalDateTime.parse(resultSet.getString(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
                session.setMovie(findMovieById(movieId));
                sessions.add(session);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    public static boolean[][] findTicketsBySessionId(Integer idSession) {
        int row = 0;
        int rowAmount = 15;
        int columnAmount = 15;
        boolean[][] tickets = new boolean[rowAmount][columnAmount];
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM db_cinema.tickets_" + idSession);
            while (resultSet.next()) {

                for (int i = 0, j = 1; i < columnAmount; i++, j++) {
                    tickets[row][i] = resultSet.getBoolean(j);
                }
                row++;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tickets;
    }

    public static List<Ticket> findTicketsByUserId(Integer idUser) {
        List<Ticket> tickets = new LinkedList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_TICKETS_BY_USER_ID);
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt(1));
                ticket.setRow(rs.getInt(2));
                ticket.setColumn(rs.getInt(3));
                ticket.setUser(findUserById(rs.getInt(4)));
                ticket.setSession(findSessionById(rs.getInt(5)));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public static boolean checkLoginToEquals(String login) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN_EQUALS);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkLoginPasswordToValid(String login, String password) {
        boolean st = false;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where login=? and password=?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            st = resultSet.next();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return st;
    }

    public static boolean isAdmin(String login, String password) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where login=? and password=?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            if (Role.valueOf(resultSet.getString(7)).equals(Role.ADMIN)) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void buyTicket(Ticket ticket) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `tickets_" + ticket.getSession().getId() + "` SET `column_" + ticket.getColumn() + "`='1' WHERE `row`=" + ticket.getRow() + ";");
            preparedStatement.executeUpdate();
            preparedStatement.close();

            Statement stmt = connection.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();

            PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_TICKET_IN_ALLTICKETS);
            preparedStatement2.setInt(1, ticket.getRow());
            preparedStatement2.setInt(2, ticket.getColumn());
            preparedStatement2.setInt(3, ticket.getUser().getId());
            preparedStatement2.setInt(4, ticket.getSession().getId());
            preparedStatement2.executeUpdate();
            preparedStatement2.close();

            Statement stmt2 = connection.createStatement();
            stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
            stmt2.close();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTicketEmpty(Integer idTickets, Integer row, Integer column) {
        boolean isTicketEmpty = false;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets_" + idTickets + " WHERE `row`=" + row);
            resultSet.next();
            if (resultSet.getInt(column) == 0) {
                isTicketEmpty = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTicketEmpty;
    }
}
