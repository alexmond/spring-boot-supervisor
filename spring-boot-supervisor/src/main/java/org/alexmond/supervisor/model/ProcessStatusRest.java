package org.alexmond.supervisor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.alexmond.supervisor.config.ProcessConfig;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a user entity in the system.
 * Implements Identifiable interface for consistent ID handling.
 */
@Data
@Schema(description = "Represents process status information")
public class ProcessStatusRest {

    @Schema(description = "Name of the process", example = "myapp")
    private String name;

    @Schema(description = "Current status of the process (e.g., running, stopped, failed)")
    private ProcessStatus status;

    @Schema(description = "Process ID assigned by the operating system", example = "1234")
    private Long pid;

    @Schema(description = "Timestamp when the process was started")
    private LocalDateTime startTime;

    @Schema(description = "Timestamp when the process ended")
    private LocalDateTime endTime;

    @Schema(description = "Process exit code", example = "0")
    private Integer exitCode;

    @Schema(description = "Process runtime duration", format = "duration")
    private Duration processRuntime;

    @Schema(description = "Formatted process uptime", example = "2d 5h 30m")
    private String processUptime;

    @Schema(description = "Process configuration settings")
    private ProcessConfig processConfig;

    public boolean isAlive() {
        return pid != null;
    }


    public ProcessStatusRest(String name,RunningProcess runningProcess) {
        this.name = name;
        this.startTime= runningProcess.getStartTime();
        this.endTime = runningProcess.getEndTime();
        this.exitCode = runningProcess.getExitCode();
        processConfig = runningProcess.getProcessConfig();
        processRuntime = runningProcess.getProcessRuntime();
        processUptime = runningProcess.getProcessRuntimeFormatted();
        if (runningProcess.getProcess() != null) {
            pid = runningProcess.getProcess().pid();
        }
        status = runningProcess.getProcessStatus();
    }
}
