// ============================================================================
//  File          : OptionalForCollections
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package github.olegts.java8.misused.optional;

import github.olegts.java8.misused.Annotations.Good;
import github.olegts.java8.misused.Annotations.Ugly;
import github.olegts.java8.misused.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalForCollections {
    private static final String ADMIN_ROLE = "admin";

    @Ugly
    class TooVerbose {
        public User findAnyAdmin() {
            Optional<List<User>> users = findUsersByRole(ADMIN_ROLE);
            if (users.isPresent() && !users.get().isEmpty()) {
                return users.get().get(0);
            }
            throw new IllegalStateException("No admins found");
        }

        private Optional<List<User>> findUsersByRole(String role) {
            //real search in DB
            return Optional.empty();
        }
    }

    @Good
    class NiceAndClean {
        public User findAnyAdmin() {
            return findUsersByRole(ADMIN_ROLE).stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No admins found"));
        }

        private List<User> findUsersByRole(String role) {
            //real search in DB
            return Collections.emptyList();
        }
    }
}
