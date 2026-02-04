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
  - [ ] Implement caching for heists (and maybe challenges)
- [ ] Web Controllers
- [ ] Exception Handling
### Notes

Fear & Greed no all bags bonus 

@NotNull used for null validation at controller level using @Valid e.g. <br>
```
@PostMapping("/users")
public ResponseEntity<User> createUser(@Valid @RequestBody User user)
{
    return ResponseEntity.ok(userRepository.save(user));
}
```
## Frontend
- [ ] Webpages