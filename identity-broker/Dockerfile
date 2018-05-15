FROM jboss/keycloak:4.0.0.Beta2

ADD identity-broker-realm.json /opt/jboss/keycloak/
CMD ["-b", "0.0.0.0", "-Dkeycloak.import=/opt/jboss/keycloak/identity-broker-realm.json", "-Dkeycloak.migration.action=export -Dkeycloak.migration.realmName=keycloak-identity-broker -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=/opt/jboss/keycloak/identitiy-broker-export.json"]