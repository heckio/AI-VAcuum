import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.Environment;
import aima.core.agent.EnvironmentListener;
import aima.core.agent.impl.SimpleActionTracker;
import aima.core.agent.impl.SimpleEnvironmentView;
import aima.core.environment.vacuum.*;
import aima.core.search.nondeterministic.NondeterministicProblem;

import java.util.ArrayList;
import java.util.List;

public class VacuumLinear {
    public static void main(String[] args) {

        //agents
        Agent<VacuumPercept,Action> sagent = new SimpleReflexVacuumAgent();
        //States
        VacuumEnvironment.LocationState clean = VacuumEnvironment.LocationState.Clean;
        VacuumEnvironment.LocationState dirty = VacuumEnvironment.LocationState.Dirty;
        //Possibilities
        Environment<VacuumPercept, Action> clean_clean = new VacuumEnvironment(clean,clean);
        Environment<VacuumPercept, Action> clean_dirty = new VacuumEnvironment(clean,dirty);
        Environment<VacuumPercept, Action> dirty_clean = new VacuumEnvironment(dirty,clean);
        Environment<VacuumPercept, Action> dirty_dirty = new VacuumEnvironment(dirty,dirty);
        //Listener
        EnvironmentListener<Object,Object> listenercc = new SimpleEnvironmentView();
        EnvironmentListener<Object,Object> listenercd = new SimpleEnvironmentView();
        EnvironmentListener<Object,Object> listenerdc = new SimpleEnvironmentView();
        EnvironmentListener<Object,Object> listenerdd = new SimpleEnvironmentView();
        //listlener assignment
        clean_clean.addEnvironmentListener(listenercc);
        clean_dirty.addEnvironmentListener(listenercd);
        dirty_clean.addEnvironmentListener(listenerdc);
        dirty_dirty.addEnvironmentListener(listenerdd);
        //locations
        List<String> locs = new ArrayList<>();
        locs.add("A");
        locs.add("B");

        //agent assignment
        clean_clean.addAgent(sagent);
        clean_clean.step(4);
        clean_clean.notify("Clean Clean" + clean_clean.getPerformanceMeasure(sagent));
        clean_dirty.addAgent(sagent);
        clean_dirty.step(4);
        clean_dirty.notify("Clean Dirty" + clean_dirty.getPerformanceMeasure(sagent));
        dirty_clean.addAgent(sagent);
        dirty_clean.step(4);
        dirty_clean.notify("Dirty Clean" + dirty_clean.getPerformanceMeasure(sagent));
        dirty_dirty.addAgent(sagent);
        dirty_dirty.step(4);
        dirty_dirty.notify("Dirty Dirty" + dirty_dirty.getPerformanceMeasure(sagent));

    }
}
