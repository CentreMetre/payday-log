# Payday Log
This is an app used to record activities in payday (Specific Payday 3, at least for now).
Current features will include heist logging and daily challenge logging.

# Task/Checklist
## DB
### Design
- [X] Heist Log
- [X] Challenge


## Backend
- [ ] Models
- [ ] Repositories
- [ ] Service
- [ ] Web Controllers
- [ ] Exception Handling
### Notes
@NotNull used for null validation at controller level using @Valid e.g. <br>
@PostMapping("/users")<br>
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {<br>
return ResponseEntity.ok(userRepository.save(user));<br>
}<br>

## Frontend
- [ ] Webpages