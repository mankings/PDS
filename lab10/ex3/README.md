# The problem
Due to lack of sleep, time and inspiration, the problem we have in hand is simply a way to manage users (definied by a name) that want to communicate in a chat.
# The solution
So, to solve this problem we used the mediator pattern. We created the interface **Chat** that has the methods or mediator (**ChatImplementation**) will use (adding members and sending a message to all of them). The method *addMember* works as a way to add a member to the current active members list, and the method *send* is used as a way to share a members message to all of them. For the members, we built the abstract class **BaseMember** that has the *send* method that calls the chat's method that was previously discussed, while logging who is sending that message. The receive method was made abstract to add different funcionalities on the different possible classes that extend **BaseMember**. Lastly, a **Main** class was created to test this implementation.
# The sources
- Theorical Classes Slides
- https://refactoring.guru/design-patterns/mediator
- https://www.baeldung.com/java-mediator-pattern