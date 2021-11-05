module adapter.controller {
    exports kalah.clean.arch.adapter.controller.gateway;

    requires kalah.clean.arch.usecase.gateway;
    requires lombok;
    requires org.slf4j;
}