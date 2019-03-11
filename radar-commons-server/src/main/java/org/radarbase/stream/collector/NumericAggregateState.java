/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.radarbase.stream.collector;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** State of the NumericAggregateCollector */
@org.apache.avro.specific.AvroGenerated
public class NumericAggregateState extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4736695739985929457L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NumericAggregateState\",\"namespace\":\"org.radarbase.stream.collector\",\"doc\":\"State of the NumericAggregateCollector\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Name of field or null if not belonging to a specific field.\",\"default\":null},{\"name\":\"pos\",\"type\":[\"null\",\"int\"],\"doc\":\"Position of field or null if not belonging to a specific field.\",\"default\":null},{\"name\":\"fieldType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Type of field or null if not belonging to a specific field.\",\"default\":null},{\"name\":\"count\",\"type\":\"long\",\"doc\":\"Number of values encountered.\"},{\"name\":\"min\",\"type\":[\"null\",\"double\"],\"doc\":\"Minimum value encountered or null if no value has been encountered.\",\"default\":null},{\"name\":\"max\",\"type\":[\"null\",\"double\"],\"doc\":\"Maximum value encountered or null if no value has been encountered.\",\"default\":null},{\"name\":\"sum\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"BigDecimalState\",\"fields\":[{\"name\":\"intVal\",\"type\":\"bytes\",\"doc\":\"java BigInteger integer value.\"},{\"name\":\"scale\",\"type\":\"int\",\"doc\":\"java BigDecimal scale.\"}]}],\"doc\":\"Sum encoded as Java BigDecimal bytes or null if no value has been encountered.\",\"default\":null},{\"name\":\"reservoir\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"SamplingReservoirState\",\"fields\":[{\"name\":\"samples\",\"type\":{\"type\":\"array\",\"items\":\"double\"},\"doc\":\"java BigInteger integer value.\"},{\"name\":\"count\",\"type\":\"long\",\"doc\":\"Number of values processed for samples.\"},{\"name\":\"maxSize\",\"type\":\"int\",\"doc\":\"Maximum size of the reservoir.\"}]}],\"doc\":\"Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.\",\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<NumericAggregateState> ENCODER =
      new BinaryMessageEncoder<NumericAggregateState>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<NumericAggregateState> DECODER =
      new BinaryMessageDecoder<NumericAggregateState>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<NumericAggregateState> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<NumericAggregateState> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<NumericAggregateState>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this NumericAggregateState to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a NumericAggregateState from a ByteBuffer. */
  public static NumericAggregateState fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Name of field or null if not belonging to a specific field. */
  @Deprecated public java.lang.String name;
  /** Position of field or null if not belonging to a specific field. */
  @Deprecated public java.lang.Integer pos;
  /** Type of field or null if not belonging to a specific field. */
  @Deprecated public java.lang.String fieldType;
  /** Number of values encountered. */
  @Deprecated public long count;
  /** Minimum value encountered or null if no value has been encountered. */
  @Deprecated public java.lang.Double min;
  /** Maximum value encountered or null if no value has been encountered. */
  @Deprecated public java.lang.Double max;
  /** Sum encoded as Java BigDecimal bytes or null if no value has been encountered. */
  @Deprecated public org.radarbase.stream.collector.BigDecimalState sum;
  /** Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered. */
  @Deprecated public org.radarbase.stream.collector.SamplingReservoirState reservoir;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NumericAggregateState() {}

  /**
   * All-args constructor.
   * @param name Name of field or null if not belonging to a specific field.
   * @param pos Position of field or null if not belonging to a specific field.
   * @param fieldType Type of field or null if not belonging to a specific field.
   * @param count Number of values encountered.
   * @param min Minimum value encountered or null if no value has been encountered.
   * @param max Maximum value encountered or null if no value has been encountered.
   * @param sum Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
   * @param reservoir Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
   */
  public NumericAggregateState(java.lang.String name, java.lang.Integer pos, java.lang.String fieldType, java.lang.Long count, java.lang.Double min, java.lang.Double max, org.radarbase.stream.collector.BigDecimalState sum, org.radarbase.stream.collector.SamplingReservoirState reservoir) {
    this.name = name;
    this.pos = pos;
    this.fieldType = fieldType;
    this.count = count;
    this.min = min;
    this.max = max;
    this.sum = sum;
    this.reservoir = reservoir;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return pos;
    case 2: return fieldType;
    case 3: return count;
    case 4: return min;
    case 5: return max;
    case 6: return sum;
    case 7: return reservoir;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.String)value$; break;
    case 1: pos = (java.lang.Integer)value$; break;
    case 2: fieldType = (java.lang.String)value$; break;
    case 3: count = (java.lang.Long)value$; break;
    case 4: min = (java.lang.Double)value$; break;
    case 5: max = (java.lang.Double)value$; break;
    case 6: sum = (org.radarbase.stream.collector.BigDecimalState)value$; break;
    case 7: reservoir = (org.radarbase.stream.collector.SamplingReservoirState)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return Name of field or null if not belonging to a specific field.
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * Name of field or null if not belonging to a specific field.
   * @param value the value to set.
   */
  public void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'pos' field.
   * @return Position of field or null if not belonging to a specific field.
   */
  public java.lang.Integer getPos() {
    return pos;
  }

  /**
   * Sets the value of the 'pos' field.
   * Position of field or null if not belonging to a specific field.
   * @param value the value to set.
   */
  public void setPos(java.lang.Integer value) {
    this.pos = value;
  }

  /**
   * Gets the value of the 'fieldType' field.
   * @return Type of field or null if not belonging to a specific field.
   */
  public java.lang.String getFieldType() {
    return fieldType;
  }

  /**
   * Sets the value of the 'fieldType' field.
   * Type of field or null if not belonging to a specific field.
   * @param value the value to set.
   */
  public void setFieldType(java.lang.String value) {
    this.fieldType = value;
  }

  /**
   * Gets the value of the 'count' field.
   * @return Number of values encountered.
   */
  public java.lang.Long getCount() {
    return count;
  }

  /**
   * Sets the value of the 'count' field.
   * Number of values encountered.
   * @param value the value to set.
   */
  public void setCount(java.lang.Long value) {
    this.count = value;
  }

  /**
   * Gets the value of the 'min' field.
   * @return Minimum value encountered or null if no value has been encountered.
   */
  public java.lang.Double getMin() {
    return min;
  }

  /**
   * Sets the value of the 'min' field.
   * Minimum value encountered or null if no value has been encountered.
   * @param value the value to set.
   */
  public void setMin(java.lang.Double value) {
    this.min = value;
  }

  /**
   * Gets the value of the 'max' field.
   * @return Maximum value encountered or null if no value has been encountered.
   */
  public java.lang.Double getMax() {
    return max;
  }

  /**
   * Sets the value of the 'max' field.
   * Maximum value encountered or null if no value has been encountered.
   * @param value the value to set.
   */
  public void setMax(java.lang.Double value) {
    this.max = value;
  }

  /**
   * Gets the value of the 'sum' field.
   * @return Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
   */
  public org.radarbase.stream.collector.BigDecimalState getSum() {
    return sum;
  }

  /**
   * Sets the value of the 'sum' field.
   * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
   * @param value the value to set.
   */
  public void setSum(org.radarbase.stream.collector.BigDecimalState value) {
    this.sum = value;
  }

  /**
   * Gets the value of the 'reservoir' field.
   * @return Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
   */
  public org.radarbase.stream.collector.SamplingReservoirState getReservoir() {
    return reservoir;
  }

  /**
   * Sets the value of the 'reservoir' field.
   * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
   * @param value the value to set.
   */
  public void setReservoir(org.radarbase.stream.collector.SamplingReservoirState value) {
    this.reservoir = value;
  }

  /**
   * Creates a new NumericAggregateState RecordBuilder.
   * @return A new NumericAggregateState RecordBuilder
   */
  public static org.radarbase.stream.collector.NumericAggregateState.Builder newBuilder() {
    return new org.radarbase.stream.collector.NumericAggregateState.Builder();
  }

  /**
   * Creates a new NumericAggregateState RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NumericAggregateState RecordBuilder
   */
  public static org.radarbase.stream.collector.NumericAggregateState.Builder newBuilder(org.radarbase.stream.collector.NumericAggregateState.Builder other) {
    return new org.radarbase.stream.collector.NumericAggregateState.Builder(other);
  }

  /**
   * Creates a new NumericAggregateState RecordBuilder by copying an existing NumericAggregateState instance.
   * @param other The existing instance to copy.
   * @return A new NumericAggregateState RecordBuilder
   */
  public static org.radarbase.stream.collector.NumericAggregateState.Builder newBuilder(org.radarbase.stream.collector.NumericAggregateState other) {
    return new org.radarbase.stream.collector.NumericAggregateState.Builder(other);
  }

  /**
   * RecordBuilder for NumericAggregateState instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NumericAggregateState>
    implements org.apache.avro.data.RecordBuilder<NumericAggregateState> {

    /** Name of field or null if not belonging to a specific field. */
    private java.lang.String name;
    /** Position of field or null if not belonging to a specific field. */
    private java.lang.Integer pos;
    /** Type of field or null if not belonging to a specific field. */
    private java.lang.String fieldType;
    /** Number of values encountered. */
    private long count;
    /** Minimum value encountered or null if no value has been encountered. */
    private java.lang.Double min;
    /** Maximum value encountered or null if no value has been encountered. */
    private java.lang.Double max;
    /** Sum encoded as Java BigDecimal bytes or null if no value has been encountered. */
    private org.radarbase.stream.collector.BigDecimalState sum;
    private org.radarbase.stream.collector.BigDecimalState.Builder sumBuilder;
    /** Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered. */
    private org.radarbase.stream.collector.SamplingReservoirState reservoir;
    private org.radarbase.stream.collector.SamplingReservoirState.Builder reservoirBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.radarbase.stream.collector.NumericAggregateState.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.pos)) {
        this.pos = data().deepCopy(fields()[1].schema(), other.pos);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.fieldType)) {
        this.fieldType = data().deepCopy(fields()[2].schema(), other.fieldType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.count)) {
        this.count = data().deepCopy(fields()[3].schema(), other.count);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.min)) {
        this.min = data().deepCopy(fields()[4].schema(), other.min);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.max)) {
        this.max = data().deepCopy(fields()[5].schema(), other.max);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.sum)) {
        this.sum = data().deepCopy(fields()[6].schema(), other.sum);
        fieldSetFlags()[6] = true;
      }
      if (other.hasSumBuilder()) {
        this.sumBuilder = org.radarbase.stream.collector.BigDecimalState.newBuilder(other.getSumBuilder());
      }
      if (isValidValue(fields()[7], other.reservoir)) {
        this.reservoir = data().deepCopy(fields()[7].schema(), other.reservoir);
        fieldSetFlags()[7] = true;
      }
      if (other.hasReservoirBuilder()) {
        this.reservoirBuilder = org.radarbase.stream.collector.SamplingReservoirState.newBuilder(other.getReservoirBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing NumericAggregateState instance
     * @param other The existing instance to copy.
     */
    private Builder(org.radarbase.stream.collector.NumericAggregateState other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.pos)) {
        this.pos = data().deepCopy(fields()[1].schema(), other.pos);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.fieldType)) {
        this.fieldType = data().deepCopy(fields()[2].schema(), other.fieldType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.count)) {
        this.count = data().deepCopy(fields()[3].schema(), other.count);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.min)) {
        this.min = data().deepCopy(fields()[4].schema(), other.min);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.max)) {
        this.max = data().deepCopy(fields()[5].schema(), other.max);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.sum)) {
        this.sum = data().deepCopy(fields()[6].schema(), other.sum);
        fieldSetFlags()[6] = true;
      }
      this.sumBuilder = null;
      if (isValidValue(fields()[7], other.reservoir)) {
        this.reservoir = data().deepCopy(fields()[7].schema(), other.reservoir);
        fieldSetFlags()[7] = true;
      }
      this.reservoirBuilder = null;
    }

    /**
      * Gets the value of the 'name' field.
      * Name of field or null if not belonging to a specific field.
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * Name of field or null if not belonging to a specific field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setName(java.lang.String value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * Name of field or null if not belonging to a specific field.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * Name of field or null if not belonging to a specific field.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'pos' field.
      * Position of field or null if not belonging to a specific field.
      * @return The value.
      */
    public java.lang.Integer getPos() {
      return pos;
    }

    /**
      * Sets the value of the 'pos' field.
      * Position of field or null if not belonging to a specific field.
      * @param value The value of 'pos'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setPos(java.lang.Integer value) {
      validate(fields()[1], value);
      this.pos = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'pos' field has been set.
      * Position of field or null if not belonging to a specific field.
      * @return True if the 'pos' field has been set, false otherwise.
      */
    public boolean hasPos() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'pos' field.
      * Position of field or null if not belonging to a specific field.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearPos() {
      pos = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'fieldType' field.
      * Type of field or null if not belonging to a specific field.
      * @return The value.
      */
    public java.lang.String getFieldType() {
      return fieldType;
    }

    /**
      * Sets the value of the 'fieldType' field.
      * Type of field or null if not belonging to a specific field.
      * @param value The value of 'fieldType'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setFieldType(java.lang.String value) {
      validate(fields()[2], value);
      this.fieldType = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'fieldType' field has been set.
      * Type of field or null if not belonging to a specific field.
      * @return True if the 'fieldType' field has been set, false otherwise.
      */
    public boolean hasFieldType() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'fieldType' field.
      * Type of field or null if not belonging to a specific field.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearFieldType() {
      fieldType = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'count' field.
      * Number of values encountered.
      * @return The value.
      */
    public java.lang.Long getCount() {
      return count;
    }

    /**
      * Sets the value of the 'count' field.
      * Number of values encountered.
      * @param value The value of 'count'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setCount(long value) {
      validate(fields()[3], value);
      this.count = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'count' field has been set.
      * Number of values encountered.
      * @return True if the 'count' field has been set, false otherwise.
      */
    public boolean hasCount() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'count' field.
      * Number of values encountered.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearCount() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'min' field.
      * Minimum value encountered or null if no value has been encountered.
      * @return The value.
      */
    public java.lang.Double getMin() {
      return min;
    }

    /**
      * Sets the value of the 'min' field.
      * Minimum value encountered or null if no value has been encountered.
      * @param value The value of 'min'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setMin(java.lang.Double value) {
      validate(fields()[4], value);
      this.min = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'min' field has been set.
      * Minimum value encountered or null if no value has been encountered.
      * @return True if the 'min' field has been set, false otherwise.
      */
    public boolean hasMin() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'min' field.
      * Minimum value encountered or null if no value has been encountered.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearMin() {
      min = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'max' field.
      * Maximum value encountered or null if no value has been encountered.
      * @return The value.
      */
    public java.lang.Double getMax() {
      return max;
    }

    /**
      * Sets the value of the 'max' field.
      * Maximum value encountered or null if no value has been encountered.
      * @param value The value of 'max'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setMax(java.lang.Double value) {
      validate(fields()[5], value);
      this.max = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'max' field has been set.
      * Maximum value encountered or null if no value has been encountered.
      * @return True if the 'max' field has been set, false otherwise.
      */
    public boolean hasMax() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'max' field.
      * Maximum value encountered or null if no value has been encountered.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearMax() {
      max = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'sum' field.
      * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
      * @return The value.
      */
    public org.radarbase.stream.collector.BigDecimalState getSum() {
      return sum;
    }

    /**
      * Sets the value of the 'sum' field.
      * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
      * @param value The value of 'sum'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setSum(org.radarbase.stream.collector.BigDecimalState value) {
      validate(fields()[6], value);
      this.sumBuilder = null;
      this.sum = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'sum' field has been set.
      * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
      * @return True if the 'sum' field has been set, false otherwise.
      */
    public boolean hasSum() {
      return fieldSetFlags()[6];
    }

    /**
     * Gets the Builder instance for the 'sum' field and creates one if it doesn't exist yet.
     * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
     * @return This builder.
     */
    public org.radarbase.stream.collector.BigDecimalState.Builder getSumBuilder() {
      if (sumBuilder == null) {
        if (hasSum()) {
          setSumBuilder(org.radarbase.stream.collector.BigDecimalState.newBuilder(sum));
        } else {
          setSumBuilder(org.radarbase.stream.collector.BigDecimalState.newBuilder());
        }
      }
      return sumBuilder;
    }

    /**
     * Sets the Builder instance for the 'sum' field
     * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setSumBuilder(org.radarbase.stream.collector.BigDecimalState.Builder value) {
      clearSum();
      sumBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'sum' field has an active Builder instance
     * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
     * @return True if the 'sum' field has an active Builder instance
     */
    public boolean hasSumBuilder() {
      return sumBuilder != null;
    }

    /**
      * Clears the value of the 'sum' field.
      * Sum encoded as Java BigDecimal bytes or null if no value has been encountered.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearSum() {
      sum = null;
      sumBuilder = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'reservoir' field.
      * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
      * @return The value.
      */
    public org.radarbase.stream.collector.SamplingReservoirState getReservoir() {
      return reservoir;
    }

    /**
      * Sets the value of the 'reservoir' field.
      * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
      * @param value The value of 'reservoir'.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setReservoir(org.radarbase.stream.collector.SamplingReservoirState value) {
      validate(fields()[7], value);
      this.reservoirBuilder = null;
      this.reservoir = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'reservoir' field has been set.
      * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
      * @return True if the 'reservoir' field has been set, false otherwise.
      */
    public boolean hasReservoir() {
      return fieldSetFlags()[7];
    }

    /**
     * Gets the Builder instance for the 'reservoir' field and creates one if it doesn't exist yet.
     * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
     * @return This builder.
     */
    public org.radarbase.stream.collector.SamplingReservoirState.Builder getReservoirBuilder() {
      if (reservoirBuilder == null) {
        if (hasReservoir()) {
          setReservoirBuilder(org.radarbase.stream.collector.SamplingReservoirState.newBuilder(reservoir));
        } else {
          setReservoirBuilder(org.radarbase.stream.collector.SamplingReservoirState.newBuilder());
        }
      }
      return reservoirBuilder;
    }

    /**
     * Sets the Builder instance for the 'reservoir' field
     * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public org.radarbase.stream.collector.NumericAggregateState.Builder setReservoirBuilder(org.radarbase.stream.collector.SamplingReservoirState.Builder value) {
      clearReservoir();
      reservoirBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'reservoir' field has an active Builder instance
     * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
     * @return True if the 'reservoir' field has an active Builder instance
     */
    public boolean hasReservoirBuilder() {
      return reservoirBuilder != null;
    }

    /**
      * Clears the value of the 'reservoir' field.
      * Uniform sampling reservoir of values encountered. Null if reservoir sampling is disabled or no value has been encountered.
      * @return This builder.
      */
    public org.radarbase.stream.collector.NumericAggregateState.Builder clearReservoir() {
      reservoir = null;
      reservoirBuilder = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NumericAggregateState build() {
      try {
        NumericAggregateState record = new NumericAggregateState();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.String) defaultValue(fields()[0]);
        record.pos = fieldSetFlags()[1] ? this.pos : (java.lang.Integer) defaultValue(fields()[1]);
        record.fieldType = fieldSetFlags()[2] ? this.fieldType : (java.lang.String) defaultValue(fields()[2]);
        record.count = fieldSetFlags()[3] ? this.count : (java.lang.Long) defaultValue(fields()[3]);
        record.min = fieldSetFlags()[4] ? this.min : (java.lang.Double) defaultValue(fields()[4]);
        record.max = fieldSetFlags()[5] ? this.max : (java.lang.Double) defaultValue(fields()[5]);
        if (sumBuilder != null) {
          record.sum = this.sumBuilder.build();
        } else {
          record.sum = fieldSetFlags()[6] ? this.sum : (org.radarbase.stream.collector.BigDecimalState) defaultValue(fields()[6]);
        }
        if (reservoirBuilder != null) {
          record.reservoir = this.reservoirBuilder.build();
        } else {
          record.reservoir = fieldSetFlags()[7] ? this.reservoir : (org.radarbase.stream.collector.SamplingReservoirState) defaultValue(fields()[7]);
        }
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<NumericAggregateState>
    WRITER$ = (org.apache.avro.io.DatumWriter<NumericAggregateState>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<NumericAggregateState>
    READER$ = (org.apache.avro.io.DatumReader<NumericAggregateState>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
