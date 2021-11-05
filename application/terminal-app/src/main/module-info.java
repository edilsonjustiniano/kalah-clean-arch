module application.spring {
    requires kalah.clean.arch.adapter.controller.gateway;
    requires kalah.clean.arch.usecase.gateway;
    requires kalah.clean.arch.usecase.exception;
    requires kalah.clean.arch.repository.gateway;

    requires lombok;
    requires org.slf4j;
}