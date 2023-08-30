
# Garde-manger (French for Pantry)

### Background
This application was for a university assignment in my mobile computing class. The application was required
to meet a specific set of requirements, e.g. Use jetpack compose, contain atleast 3 screens, allow for a second langauge etc.

Garde-manger gives a simplified way of making a shopping list specifically for the meals you 
are making in the coming days. Create meals and tick off the ingredients you have, then in one central
place you can see all the ingredients you need to by next time you're at the supermarket.
#### Key Learnings
 - Familiarisation with Kotlin programming language
 - Introduction to the Android ecosystem and Android Studio
 - Increased understanding of MVVM architecture and Dependency injection.
 - Design considerations making an application for a native purpose rather than Web applications.

#### Development Process

1. I started development watching a youtube video about persistence and laying out my project in a clean way.
2. This led me to learn about hilt which helped to clean up my development.
3. I then made static screens for my whole app, followed by adding navigation to these screens.
4. Finally I added events and view models to implement the CRUD actions of the application.

-------

### Post Project Reflection
I am happy with my learning from this project but can definitely see some limitations with it. In particular this application has some issues
on the service layer. The entities override eachother in places where they shouldn't. This was a conscious decision as the scope of the assignment was focussed on making the application interface. If I was to make this application again I would produce a much more robust service and database layer. I also think the UI is not very exciting however it is functional and that was the main requirement.

Finally, I really enjoyed my development experience with Jetback Compose and with dagger hilt and room. If I was to make this app again though I would like to experiment with React native or Flutter.
