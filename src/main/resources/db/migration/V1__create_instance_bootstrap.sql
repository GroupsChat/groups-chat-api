CREATE TABLE instances (
    id UUID PRIMARY KEY,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT instances_status_check
        CHECK (status IN ('UNINITIALIZED', 'READY'))
);

CREATE TABLE instance_settings (
    instance_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    icon VARCHAR(255),
    join_policy VARCHAR(30) NOT NULL,
    version VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT instance_settings_join_policy_check
        CHECK (join_policy IN ('PUBLIC', 'APPROVAL_REQUIRED', 'CLOSED')),
    CONSTRAINT instance_settings_instance_fk
        FOREIGN KEY (instance_id) REFERENCES instances (id) ON DELETE CASCADE
);