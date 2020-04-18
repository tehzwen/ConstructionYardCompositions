class Logger {
    constructor() {
        this.currentTime = new Date();
        this.route = "/";
        this.currentRequest = {};
    }

    logRequest(route, method, request) {
        this.route = route;
        this.currentRequest = request;
        this.currentTime = new Date();

        let log = {
            status: "REQUEST",
            date: this.currentTime.toISOString(),
            route,
            method
        };

        if (Object.keys(request.params).length > 0) {
            log.params = request.params;
        }
        if (Object.keys(request.body).length > 0) {
            log.body = request.body;
        }

        console.log(JSON.stringify(log));
    }

    logError(route, method, request) {
        this.route = route;
        this.currentRequest = request;
        this.currentTime = new Date();

        let log = {
            status: "ERROR",
            date: this.currentTime.toISOString(),
            route,
            method
        };

        if (Object.keys(request.params).length > 0) {
            log.params = request.params;
        }
        if (Object.keys(request.body).length > 0) {
            log.body = request.body;
        }

        console.error(JSON.stringify(log));
    }

    logDatabase(results, query) {
        this.currentTime = new Date();
        let log = {
            status: "DATABASE",
            date: this.currentTime.toISOString(),
            query,
            results
        };

        console.log(JSON.stringify(log));
    }
}

module.exports = Logger;