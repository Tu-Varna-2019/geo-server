INSERT INTO "UserType" ("type")
SELECT 'customer'
WHERE NOT EXISTS (
    SELECT 1 FROM "UserType" WHERE "type" = 'customer'
);

INSERT INTO "UserType" ("type")
SELECT 'admin'
WHERE NOT EXISTS (
    SELECT 1 FROM "UserType" WHERE "type" = 'admin'
);

INSERT INTO "UserType" ("type")
SELECT 'superadmin'
WHERE NOT EXISTS (
    SELECT 1 FROM "UserType" WHERE "type" = 'superadmin'
);