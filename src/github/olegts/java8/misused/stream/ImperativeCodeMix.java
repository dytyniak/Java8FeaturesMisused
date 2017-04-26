// ============================================================================
//  File          : ImparativeCodeMix
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package github.olegts.java8.misused.stream;

import github.olegts.java8.misused.Role;
import github.olegts.java8.misused.Annotations.Good;
import github.olegts.java8.misused.Annotations.Ugly;
import github.olegts.java8.misused.Permission;
import github.olegts.java8.misused.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImperativeCodeMix {
    private static final String ADMIN_ROLE = "admin";

    private final List<User> users = new ArrayList<>();

    @Ugly
    class TooVerboseMixOfStreamOperationsAndImperativeCode {
        public boolean hasAdmin() {
            return users.stream()
                    .map(u -> {
                        if (u == null) {
                            throw new NullPointerException();
                        }
                        return u;
                    })
                    .flatMap(u -> u.getRoles().stream())
                    .map(Role::getName)
                    .anyMatch(name -> ADMIN_ROLE.equals(name));
        }
    }

    @Good
    class NiceAndCleanStreamOperationsChain {
        public boolean hasAdmin(Permission permission) {
            return users.stream()
                    .map(Objects::requireNonNull)
                    .flatMap(u -> u.getRoles().stream())
                    .map(Role::getName)
                    .anyMatch(ADMIN_ROLE::equals);
        }
    }
}
