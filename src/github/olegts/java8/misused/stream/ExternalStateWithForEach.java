// ============================================================================
//  File          : MultiLevelForEach
//  Created       : 29.08.2016   
//  Description   :
//  Modifications :
//
// ============================================================================
//  Copyright(c) 2016 XP Injection, Ukraine
// ============================================================================
package github.olegts.java8.misused.stream;

import github.olegts.java8.misused.Annotations;
import github.olegts.java8.misused.Annotations.Good;
import github.olegts.java8.misused.Annotations.Ugly;
import github.olegts.java8.misused.Permission;
import github.olegts.java8.misused.User;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExternalStateWithForEach {
    private final Set<User> users = new HashSet<>();

    @Ugly
    class UseOldSchoolIterationsWithForEachAndExternalBoolean {
        public boolean checkPermission(Permission permission) {
            AtomicBoolean found = new AtomicBoolean();
            users.forEach(
                    u -> u.getRoles().forEach(
                            r -> {
                                if (r.getPermissions().contains(permission)) {
                                    found.set(true);
                                }
                            }
                    )
            );
            return found.get();
        }
    }

    @Good
    class UseFlatMapForSubCollections {
        public boolean checkPermission(Permission permission) {
            return users.stream()
                    .flatMap(u -> u.getRoles().stream())
                    .anyMatch(r -> r.getPermissions().contains(permission));
        }
    }
}
