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
- [ ] Implement data exceptions such as Road Rage can't be finished on stealth.
### Notes

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
- [ ] Completed Heist

- [ ] Verify data input against other input, e.g. XP, challenge name.