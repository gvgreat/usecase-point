/**
 * @Copyrights G. Vaidhyanathan
 */
package org.ucp.pf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;

/**
 * @author G. Vaidhyanathan
 */
public class ProductivityFactor {
  private Integer productivity;
  private final List<Action> actions = new ArrayList<Action>(1);

  public ProductivityFactor(Action... actions_p) {
    actions.addAll(Arrays.asList(actions_p));
    setProductivity(Integer.valueOf(0));
  }

  public void addAction(Action a) {
    actions.add(a);
  }

  /**
   * @return the productivity
   */
  public Integer getProductivity() {
    return productivity;
  }

  /**
   * @param productivity_p
   *          the productivity to set
   */
  @SuppressWarnings("boxing")
  public void setProductivity(Integer productivity_p) {
    this.productivity = productivity_p;
    for (Action action : actions) {
      action.setEnabled((productivity > 0));
    }
  }
}
