module application.spring {
    requires kalah.clean.arch.adapter.controller.gateway;
    requires kalah.clean.arch.usecase.gateway;
    requires kalah.clean.arch.usecase.exception;
    requires kalah.clean.arch.repository.gateway;

    requires lombok;
    requires org.slf4j;

    requires spring.web;
    requires spring.beans;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires com.fasterxml.jackson.databind;
    requires jackson.annotations;

    opens kalah.clean.arch.application.spring.config to spring.core;
}