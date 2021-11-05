module usecase {
    exports kalah.clean.arch.usecase.gateway;
    exports kalah.clean.arch.usecase.exception;

    requires kalah.clean.arch.domain;
    requires kalah.clean.arch.repository.gateway;
    requires lombok;
    requires org.slf4j;
}