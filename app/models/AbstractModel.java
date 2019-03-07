package models;

import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * モデルのベースクラス.
 *
 * @author cyrus
 */
@Constraints.Validate
@MappedSuperclass
public abstract class AbstractModel extends Model implements Constraints.Validatable<List<ValidationError>> {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    /**
     * 作成日時.
     */
    @CreatedTimestamp
    public Date createDate;

    /**
     * 更新日時.
     */
    @UpdatedTimestamp
    public Date updateDate;

    /**
     * バージョン(楽観排他用).
     */
    @Version
    public Long version;
}