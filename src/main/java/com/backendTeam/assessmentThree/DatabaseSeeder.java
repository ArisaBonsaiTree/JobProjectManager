package com.backendTeam.assessmentThree;

import com.backendTeam.assessmentThree.embeddables.Credentials;
import com.backendTeam.assessmentThree.embeddables.Profile;
import com.backendTeam.assessmentThree.entities.*;
import com.backendTeam.assessmentThree.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private CompanyRepository companyRepository;
    private TeamRepository teamRepository;
    private ProjectRepository projectRepository;

    private UserRepository userRepository;
    private AnnouncementRepository announcementRepository;

    private final int MAX = 5;


    @Override
    public void run(String... args) throws Exception {

        Random random = new Random();

        // Company
        String[] companyNames = new String[]{
                "CookSys", "Rempel - Stiedemann", "Zboncak LLC", "Vandervort - Yost", "Dibbert, VonRueden and Barrows",
                "Harris and Sons", "Streich and Sons", "King - Stamm", "Mayert - Dicki", "Boehm"
        };

        String[] companyDescription = new String[]{
                "Success is not final, failure is not fatal: it is the courage to continue that counts",
                "Success usually comes to those who are too busy to be looking for it.",
                "Opportunities don't happen. You create them.",
                "Try not to become a man of success. Rather become a man of value.",
                "Never give in, except to convictions of honor and good sense.",
                "If you are not willing to risk the usual, you will have to settle for the ordinary.",
                "All progress takes place outside the comfort zone.",
                "The successful warrior is the average man, with laser-like focus.",
                "There are no secrets to success. It is the result of preparation, hard work and learning from failure."
        };


        ArrayList<Company> listOfCompanies = new ArrayList<>();
        for(int i = 0; i < MAX; i++){
            Company company = new Company();
            company.setName(companyNames[i]);
            company.setDescription(companyDescription[i]);
            listOfCompanies.add(company);
        }
        companyRepository.saveAllAndFlush(listOfCompanies);

        // =================================================
        // Team
        String[] teamNames = new String[]{"The Great Sailors", "The Howlers", "The Majestic Blizzards",
                "The Storm Hamsters", "The Defiant Cardinals", "The Blue Gargoyles"
        };

        String[] teamDescription = new String[]{
            "The title of Ashen One is given to the player by the Fire Keeper of Firelink Shrine. ",
            "Sprigatito is a quadrupedal feline Pokémon covered in pale green fur. The fur's composition is similar to that of plants, allowing it to absorb sunlight for energy",
            "Microsoft introduced an operating environment named Windows",
            "Luxray resembles a fully-grown lion with a black and blue coat and a spiked mane.",
            "Team description"
        };

        ArrayList<Team> listOfTeams = new ArrayList<>();
        for(int i = 0; i < MAX; i++){
            Team team = new Team();
            team.setName(teamNames[i]);
            team.setDescription(teamDescription[i]);
            team.setCompany(listOfCompanies.get(i));
            listOfTeams.add(team);
        }
        teamRepository.saveAllAndFlush(listOfTeams);

        // =================================================
        // Project
        String[] projectNames = new String[]{"Hello World", "Twitter API", "Dev Duel", "Spotify Clone", "Final Project"};
        String[] projectDescription = new String[]{"Simple hello world program", "A Twitter clone",
            "Who is the better developer?", "Building a Spotify clone in one week!", "Front-end and back-end group project"
        };

        boolean[] statusList = new boolean[]{true, false};

        ArrayList<Project> listOfProjects = new ArrayList<>();
        for(int i = 0; i < MAX; i++){
            Project project = new Project();
            project.setName(projectNames[i]);
            project.setDescription(projectDescription[i]);
            project.setActive(statusList[random.nextInt(statusList.length)]);

            project.setTeam(listOfTeams.get(i));
            listOfProjects.add(project);
        }
        projectRepository.saveAllAndFlush(listOfProjects);



        // =================================================
        // User
        String[] userEmail = new String[]{"email@gmail.com", "test@gmail.com", "john@gmail.com",
            "luke@gmail.com", "admin@gmail.com",
                "user@gmail.com", "brit@gmail.com", "Discord@gmail.com", "Aqua@gmail.com", "YouTube@gmail.com"
        };
        String[] userPassword = new String[]{"Password1", "Password1", "Password1", "Password1", "Password1",
            "Password1", "Password1", "Password1", "Password1", "Password1" };
        String[] userFirstName = new String[]{"John", "Luke", "Pomu", "Water", "Sarah",
        "User", "Spiffing", "Discord", "Akutan", "Leah"};

        String[] userLastName = new String[]{"Smith", "Case", "Deleon", "Abbott", "Hull",
        "Clerihew", "Ally", "Leyva", "Minollo", "Taqqu"};

        String[] userPhoneNumber = new String[]{"111-111-1111", "222-222-2222", "333-333-3333", "444-444-4444",
        "555-5555", "666-666", "777-777", "888-8888", "999-9999", "1010-1010"};
        boolean[] userActive = new boolean[]{true, false};
        boolean[] userAdmin = new boolean[]{true, false};


        ArrayList<User> listOfUsers = new ArrayList<>();

        for(int i = 0; i < MAX; i++){

            User user = new User();
            user.setEmail(userEmail[i]);

            Credentials credentials = new Credentials();
            credentials.setPassword(userPassword[i]);
            user.setCredentials(credentials);

            Profile profile = new Profile();
            profile.setFirstName(userFirstName[i]);
            profile.setLastName(userLastName[i]);
            profile.setPhone(userPhoneNumber[i]);
            user.setProfile(profile);
            user.setActive(userActive[0]);
            user.setAdmin(userAdmin[random.nextInt(userAdmin.length)]);

            if(user.isActive()){
                user.setStatus("active");
            }else if(!user.isActive()){
                user.setStatus("inactive");
            }

            user.setTeam(listOfTeams.get(i));
            user.setCompany(listOfCompanies.get(i));

            int secondUser = MAX + i;

            User userTwo = new User();
            userTwo.setEmail(userEmail[secondUser]);

            Credentials credentials1 = new Credentials();
            credentials1.setPassword(userPassword[secondUser]);
            userTwo.setCredentials(credentials1);

            Profile profileTwo = new Profile();

            profileTwo.setFirstName(userFirstName[secondUser]);
            profileTwo.setFirstName(userFirstName[secondUser]);
            profileTwo.setLastName(userLastName[secondUser]);
            profileTwo.setPhone(userPhoneNumber[secondUser]);

            userTwo.setProfile(profileTwo);

            userTwo.setActive(userActive[0]);
            userTwo.setAdmin(userAdmin[random.nextInt(userAdmin.length)]);

            if(userTwo.isActive()){
                userTwo.setStatus("active");
            }else if(!userTwo.isActive()){
                userTwo.setStatus("inactive");
            }

            userTwo.setTeam(listOfTeams.get(i));
            userTwo.setCompany(listOfCompanies.get(i));


            listOfUsers.add(user);
            listOfUsers.add(userTwo);
        }

        userRepository.saveAllAndFlush(listOfUsers);


        // =================================================


        String[] announcementsTitle = new String[]{
            "Welcome to the Final Project",
            "This is a title",
            "IMPORTANT TITLE",
            "WARNING TITLE",
            "This is the final title!"
        };

        String[] announcementsMessage = new String[]{
            "Hope everyone has a great time working on the final project",
            "We are sorry that services are subject to delay because of a slow running preceding freight train running behind schedule",
            "The next train to arrive at platform 23 is not in passenger service. Please do not board the next train at platform",
            "This is a platform alteration.",
            "This program will terminate here."
        };

        ArrayList<Announcements> listOfAnnouncements = new ArrayList<>();

        for(int i = 0; i < MAX; i++){
            Announcements announcement = new Announcements();
            announcement.setTitle(announcementsTitle[i]);
            announcement.setMessage(announcementsMessage[i]);
            announcement.setUser(listOfUsers.get(i));
            announcement.setCompany(listOfCompanies.get(i));
            listOfAnnouncements.add(announcement);

            Announcements randomAnnouncement = new Announcements();


            randomAnnouncement.setTitle(announcementsTitle[random.nextInt(announcementsTitle.length)]);
            randomAnnouncement.setMessage(announcementsMessage[random.nextInt(announcementsMessage.length)]);



            randomAnnouncement.setUser(listOfUsers.get(MAX + i));
            randomAnnouncement.setCompany(listOfCompanies.get(i));
            listOfAnnouncements.add(randomAnnouncement);
        }

        announcementRepository.saveAllAndFlush(listOfAnnouncements);

    }
}
