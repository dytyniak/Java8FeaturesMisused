// ============================================================================
//  File          : StrictCheckOfValuePresence
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package github.olegts.java8.misused.optional;

import github.olegts.java8.misused.Annotations;
import github.olegts.java8.misused.Annotations.Good;
import github.olegts.java8.misused.Annotations.Ugly;
import github.olegts.java8.misused.User;

import java.util.Optional;

public class StrictCheckOfValuePresence {
    @Ugly
    class ManualCheckForPresenceToThrowException {
        public String getUserName(Long userId) {
            Optional<User> user = findById(userId);
            if (user.isPresent()) {
                return user.get().getName();
            }
            throw new IllegalStateException("User not found");
        }
    }

    @Good
    class OrElseThrowUsage {
        public String getUserName(Long userId) {
            return findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User not found"))
                    .getName();
        }
    }

    private Optional<User> findById(Long userId) {
        //search in DB
        return Optional.of(new User(5L, "Mikalai"));
    }
}
