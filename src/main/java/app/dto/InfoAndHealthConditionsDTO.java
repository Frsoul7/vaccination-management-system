package app.dto;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class InfoAndHealthConditionsDTO {
    /**
     * Sns user name
     */
    private String name;

    /***
     * Sns user age
     */
    private int age;

    /***
     * Sns user adverse reaction records
     */
    private String adverseReactions;

    /***
     * InfoAndHealthConditionsDTO constructor
     *
     * @param name - sns user name
     * @param age - sns user age
     * @param adverseReactions - sns user adverse reactions records
     */
    public InfoAndHealthConditionsDTO(String name, int age, String adverseReactions) {
        setName(name);
        setAge(age);
        setAdverseReactions(adverseReactions);
    }

    /***
     * Get sns user name
     * @return sns user name
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return name;
    }

    /***
     * Get sns user age
     * @return sns user age
     */
    @ExcludeFromJacocoGeneratedReport
    public int getAge() {
        return age;
    }

    /***
     * Get sns user adverse reactions records
     * @return sns user adverse reactions records
     */
    @ExcludeFromJacocoGeneratedReport
    public String getAdverseReactions() {
        return adverseReactions;
    }

    /***
     * Set sns user name
     * @param name - sns user name
     */
    @ExcludeFromJacocoGeneratedReport
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Set sns user age
     * @param age - sns user age
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAge(int age) {
        this.age = age;
    }

    /***
     * Set sns user adverse reactions records
     * @param adverseReactions - sns user adverse reactions records
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAdverseReactions(String adverseReactions) {
        this.adverseReactions = adverseReactions;
    }

    /***
     * Gives back some sns user attributes
     * @return partial description of the sns user related attributes
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("Name: %s\nAge: %d\nAdverse reactions: %s\n", this.getName(), this.getAge(),
                             this.getAdverseReactions());
    }
}
